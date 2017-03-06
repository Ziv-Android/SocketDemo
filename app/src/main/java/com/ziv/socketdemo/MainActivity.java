package com.ziv.socketdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_single_party_client)
    Button singlePartyClientBtn;
    @BindView(R.id.btn_single_party_server)
    Button singlePartyServerBtn;
    @BindView(R.id.btn_multi_party_client)
    Button multiPartyClientBtn;
    @BindView(R.id.btn_multi_party_server)
    Button multiPartyServerBtn;
    @BindView(R.id.btn_shared_info_client)
    Button sharedInfoClientBtn;
    @BindView(R.id.btn_shared_info_server)
    Button sharedInfoServerBtn;
    @BindView(R.id.btn_shared_file_client)
    Button sharedFileClientBtn;
    @BindView(R.id.btn_shared_file_server)
    Button sharedFileServerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_single_party_client)
    public void singlePartyClient() {
        //com.ziv.socketdemo.singleparty.SocketClient.
    }
}
