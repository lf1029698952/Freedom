package com.bmob.im.demo.ui;

import android.os.Bundle;
import android.widget.EditText;
import cn.bmob.v3.listener.UpdateListener;

import com.bmob.im.demo.R;
import com.bmob.im.demo.bean.User;
import com.bmob.im.demo.view.HeaderLayout.onRightImageButtonClickListener;

/**
 * �����ǳƺ��Ա�
 * 
 * @ClassName: SetNickAndSexActivity
 * @Description: TODO
 * @author smile
 * @date 2014-6-7 ����4:03:40
 */
public class UpdateInfoActivity extends ActivityBase {

	EditText edit_nick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_updateinfo);
		initView();
	}

	private void initView() {
		initTopBarForBoth("�޸��ǳ�", R.drawable.base_action_bar_true_bg_selector,
				new onRightImageButtonClickListener() {

					@Override
					public void onClick() {
						// TODO Auto-generated method stub
						String nick = edit_nick.getText().toString();
						if (nick.equals("")) {
							ShowToast("����д�ǳ�!");
							return;
						}
						updateInfo(nick);
					}
				});
		edit_nick = (EditText) findViewById(R.id.edit_nick);
	}

	/** �޸�����
	  * updateInfo
	  * @Title: updateInfo
	  * @return void
	  * @throws
	  */
	private void updateInfo(String nick) {
		final User user = userManager.getCurrentUser(User.class);
		user.setNick(nick);
		user.setSex(true);
		user.update(this, new UpdateListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				ShowToast("�޸ĳɹ�");
				finish();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				ShowToast("onFailure:" + arg1);
			}
		});
	}
}
