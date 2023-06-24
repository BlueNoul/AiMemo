package mobile_project.memo;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import mobile_project.memo.GPTAPI;

import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;




public class Main_write extends Fragment {


    public EditText text;
    public Main_write() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Main_write newInstance(String param1, String param2) {
        Main_write fragment = new Main_write();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_write, container, false);

        Button save = v.findViewById(R.id.memo_save);
        text = v.findViewById(R.id.memo_text);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Window window = getActivity().getWindow();
                new WindowInsetsControllerCompat(window, window.getDecorView()).hide(WindowInsetsCompat.Type.ime());
                String memoText = text.getText().toString();
                generateTextAsync(memoText);
                Toast.makeText(getActivity(),"메모가 저장되었습니다", Toast.LENGTH_SHORT).show();
            }

        });


        return v;
    }
    private void generateTextAsync(String prompt) {
        AsyncTask.execute(() -> {
            try {
                GPTAPI gptApi = new GPTAPI();
                String response = gptApi.generateText(prompt);
                EventBus.getDefault().post(new GPTResponseEvent(response));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGPTResponseEvent(GPTResponseEvent event) {
        String response = event.getResponse();
        // GPT 응답을 처리하여 메모 객체로 변환
        MemoObject mob= new MemoObject(response);
        //번들로 전송
        Bundle bundle = new Bundle();
        bundle.putSerializable("memoData",mob);
    }


}