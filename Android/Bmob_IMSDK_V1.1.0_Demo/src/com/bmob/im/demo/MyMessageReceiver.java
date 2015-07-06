package com.bmob.im.demo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobNotifyManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.bean.BmobInvitation;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.im.config.BmobConfig;
import cn.bmob.im.config.BmobConstant;
import cn.bmob.im.db.BmobDB;
import cn.bmob.im.inteface.EventListener;
import cn.bmob.im.util.BmobJsonUtil;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.listener.FindListener;

import com.bmob.im.demo.ui.MainActivity;
import com.bmob.im.demo.ui.NewFriendActivity;
import com.bmob.im.demo.util.CollectionUtils;
import com.bmob.im.demo.util.CommonUtils;

/**
 * ������Ϣ������
 * 
 * @ClassName: MyMessageReceiver
 * @Description: TODO
 * @author smile
 * @date 2014-5-30 ����4:01:13
 */
public class MyMessageReceiver extends BroadcastReceiver {

	// �¼�����
	public static ArrayList<EventListener> ehList = new ArrayList<EventListener>();
	
	public static final int NOTIFY_ID = 0x000;
	public static int mNewNum = 0;//
	BmobUserManager userManager;
	BmobChatUser currentUser;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String json = intent.getStringExtra("msg");
		BmobLog.i("�յ���message = " + json);
		userManager = BmobUserManager.getInstance(context);
		currentUser = userManager.getCurrentUser();
		boolean isNetConnected = CommonUtils.isNetworkAvailable(context);
		if(isNetConnected){
			parseMessage(context, json);
		}else{
			for (int i = 0; i < ehList.size(); i++)
				((EventListener) ehList.get(i)).onNetChange(isNetConnected);
		}
	}

	/** ����Json�ַ���
	  * @Title: parseMessage
	  * @Description: TODO
	  * @param @param context
	  * @param @param json 
	  * @return void
	  * @throws
	  */
	private void parseMessage(final Context context, String json) {
		JSONObject jo;
		try {
			jo = new JSONObject(json);
			String tag = BmobJsonUtil.getString(jo, BmobConstant.PUSH_KEY_TAG);
			if(tag.equals(BmobConfig.TAG_OFFLINE)){//����֪ͨ
				if(currentUser!=null){
					if (ehList.size() > 0) {// �м�����ʱ�򣬴�����ȥ
						for (EventListener handler : ehList)
							handler.onOffline();
					}else{
						//�������
						CustomApplcation.getInstance().logout();
					}
				}
			}else{
				String fromId = BmobJsonUtil.getString(jo, BmobConstant.PUSH_KEY_TARGETID);
			   //������Ϣ���շ���ObjectId--Ŀ���ǽ�����˻���½ͬһ�豸ʱ���޷����յ��ǵ�ǰ��½�û�����Ϣ��
				String toId = BmobJsonUtil.getString(jo, BmobConstant.PUSH_KEY_TOID);
				if(fromId!=null && !BmobDB.create(context,toId).isBlackUser(fromId)){//����Ϣ���ͷ���Ϊ�������û�
					if(TextUtils.isEmpty(tag)){//��Я��tag��ǩ
						//��װBmobMessage����
						BmobMsg msg =BmobMsg.createReceiveMsg(context,json);
						if(currentUser!=null){//��ǰ�豸û���˺��ڵ�½
							if( toId.equals(currentUser.getObjectId())){//ֻ�е�toId�͵�ǰ��½�û�����ϲż������´��ݺ͵�֪ͨ����ֹ���˶���ˢ��Ϣ
								// ��ͨ��Ϣ��
								if (ehList.size() > 0) {// �м�����ʱ�򣬴�����ȥ
									for (int i = 0; i < ehList.size(); i++) {
										((EventListener) ehList.get(i)).onMessage(msg);
									}
								} else {
									//�洢���յ�����Ϣ,��������Ϣ��ִ���Է�
									BmobChatManager.getInstance(context).saveReceiveMessage(true,msg);
									boolean isAllow = CustomApplcation.getInstance().getSpUtil().isAllowPushNotify();
									if(isAllow && currentUser!=null && currentUser.getObjectId().equals(toId)){//��ǰ��½�û����ڲ���Ҳ���ڽ��շ�id
										mNewNum++;
										showNotification(context,msg);
									}
								}
							}else{//��ǰ��½�˺Ų���toId������£�ҲӦ�ô洢�յ�����Ϣ��
								BmobChatManager.getInstance(context).saveReceiveMessage(true,msg);
							}
						}else{//��֮ǰ���û�Bע����½�ˣ�����û�е�½�����豸����ôA����B������Ϣ��Ӧ�ô洢����ӦB�˻������ݿ���
							BmobChatManager.getInstance(context).saveReceiveMessage(true,msg);
						}
					}else{//��tag��ǩ
						if(tag.equals(BmobConfig.TAG_ADD_CONTACT)){
							BmobInvitation message =BmobInvitation.createReceiverInvitation(json);
							//�������������Ϣ
							BmobDB.create(context,toId).saveInviteMessage(message);
							if(currentUser!=null){//�е�½�û�
								if(toId.equals(currentUser.getObjectId())){
									if (ehList.size() > 0) {// �м�����ʱ�򣬴�����ȥ
										for (EventListener handler : ehList)
											handler.onAddUser(message);
									}else{
										boolean isAllow = CustomApplcation.getInstance().getSpUtil().isAllowPushNotify();
										boolean isAllowVoice = CustomApplcation.getInstance().getSpUtil().isAllowVoice();
										boolean isAllowVibrate = CustomApplcation.getInstance().getSpUtil().isAllowVibrate();
										if(isAllow && currentUser!=null && currentUser.getObjectId().equals(toId)){
											//ͬʱ����֪ͨ
											String tickerText = message.getFromname()+"������Ӻ���";
											BmobNotifyManager.getInstance(context).showNotify(isAllowVoice,isAllowVibrate,R.drawable.ic_launcher, tickerText, message.getFromname(), tickerText.toString(),NewFriendActivity.class);
										}
									}
								}
							}
						}else if(tag.equals(BmobConfig.TAG_ADD_AGREE)){
							String username = BmobJsonUtil.getString(jo, BmobConstant.PUSH_KEY_TARGETUSERNAME);
							//�յ��Է���ͬ������֮�󣬾͵���ӶԷ�Ϊ����--��Ĭ�����ͬ�ⷽΪ���ѣ������浽���غ������ݿ�
							BmobUserManager.getInstance(context).addContactAfterAgree(username, new FindListener<BmobChatUser>() {
								
								@Override
								public void onError(int arg0, final String arg1) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onSuccess(List<BmobChatUser> arg0) {
									// TODO Auto-generated method stub
									//���浽�ڴ���
									CustomApplcation.getInstance().setContactList(CollectionUtils.list2map(BmobDB.create(context).getContactList()));
								}
							});
							
							boolean isAllow = CustomApplcation.getInstance().getSpUtil().isAllowPushNotify();
							boolean isAllowVoice = CustomApplcation.getInstance().getSpUtil().isAllowVoice();
							boolean isAllowVibrate = CustomApplcation.getInstance().getSpUtil().isAllowVibrate();
							if(isAllow && currentUser!=null && currentUser.getObjectId().equals(toId)){
								String tickerText = username+"ͬ�������Ϊ����";
								BmobNotifyManager.getInstance(context).showNotify(isAllowVoice,isAllowVibrate,R.drawable.ic_launcher, tickerText, username, tickerText.toString(),MainActivity.class);
							}
							//����һ����ʱ��֤�Ự--�����ڻỰ�����γɳ�ʼ�Ự
							BmobMsg.createAndSaveRecentAfterAgree(context, json);
							
						}else if(tag.equals(BmobConfig.TAG_READED)){//�Ѷ���ִ
							String conversionId = BmobJsonUtil.getString(jo,BmobConstant.PUSH_READED_CONVERSIONID);
							String msgTime = BmobJsonUtil.getString(jo,BmobConstant.PUSH_READED_MSGTIME);
							if(currentUser!=null){
								//����ĳ����Ϣ��״̬
								BmobChatManager.getInstance(context).updateMsgStatus(BmobConfig.STATUS_SEND_RECEIVERED, conversionId, msgTime);
								if(toId.equals(currentUser.getObjectId())){
									if (ehList.size() > 0) {// �м�����ʱ�򣬴�����ȥ--�����޸Ľ���
										for (EventListener handler : ehList)
											handler.onReaded(conversionId, msgTime);
									}
								}
							}
						}
					}
				}else{
					BmobLog.i("����Ϣ���ͷ�Ϊ�������û�");
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BmobLog.i("parseMessage����"+e.getMessage());
		}
	}
	
	/** 
	 *  ��ʾ֪ͨ
	  * @Title: showNotify
	  * @return void
	  * @throws
	  */
	public void showNotification(Context context,BmobMsg msg) {
		// ����֪ͨ��
		int icon = R.drawable.ic_launcher;
		String trueMsg = "";
		if(msg.getMsgType()==BmobConfig.TYPE_TEXT && msg.getContent().contains("\\ue")){
			trueMsg = "[����]";
		}else if(msg.getMsgType()==BmobConfig.TYPE_IMAGE){
			trueMsg = "[ͼƬ]";
		}else if(msg.getMsgType()==BmobConfig.TYPE_VOICE){
			trueMsg = "[����]";
		}else if(msg.getMsgType()==BmobConfig.TYPE_LOCATION){
			trueMsg = "[λ��]";
		}else{
			trueMsg = msg.getContent();
		}
		CharSequence tickerText = msg.getBelongUsername() + ":" + trueMsg;
		String contentTitle = msg.getBelongUsername()+ " (" + mNewNum + "������Ϣ)";
		
		Intent intent = new Intent(context, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		boolean isAllowVoice = CustomApplcation.getInstance().getSpUtil().isAllowVoice();
		boolean isAllowVibrate = CustomApplcation.getInstance().getSpUtil().isAllowVibrate();
		
		BmobNotifyManager.getInstance(context).showNotifyWithExtras(isAllowVoice,isAllowVibrate,icon, tickerText.toString(), contentTitle, tickerText.toString(),intent);
		
	}
	
}
