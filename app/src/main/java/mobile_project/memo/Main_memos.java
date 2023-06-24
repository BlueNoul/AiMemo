package mobile_project.memo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


import java.util.ArrayList;


public class Main_memos extends Fragment {

    ArrayList<String> memoCategory;
    ArrayList<String> memotexts;
    MemoAdapter adapter2;
    EditText searchtext;
    ArrayList<String> copylist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memoCategory = new ArrayList<>();
        memotexts = new ArrayList<>();
        copylist = new ArrayList<>();
        Bundle bundle = getArguments();

        //---GPT 토큰 만료로 인한 비활성화
//        MemoObject item = (MemoObject) bundle.getSerializable("memoData");
//        memoCategory.add(item.category);
//        memotexts.add(item.memoContent);

        memoCategory.add("전체보기");
        memoCategory.add("여행");
        memoCategory.add("정보");
        memotexts.add("기말고사 23일 종료.");
        memotexts.add("25일날 일본여행가기.");
        memotexts.add("메모는 기억해야 하는 내용을 적은 글 또는 그 행위를 말한다.");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_main_memos, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.category_view);
        RecyclerView recyclerView2 = v.findViewById(R.id.memos_view);
        searchtext = v.findViewById(R.id.search_text);

        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String searchText = searchtext.getText().toString();


                if(searchText.equals("")){
                    adapter2.setItems(memotexts);
                }
                else {
                    copylist.clear();
                    // 검색 단어를 포함하는지 확인
                    for (int i = 0; i < memotexts.size(); i++) {
                        if (memotexts.get(i).toLowerCase().contains(searchText.toLowerCase())) {
                            copylist.add(memotexts.get(i));
                        }
                        adapter2.setItems(copylist);
                    }
                }
            }
        });

        // 카테고리 목록 추가
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(memoCategory);
        recyclerView.setAdapter(adapter) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false));
        // 메모 목록 추가
        adapter2 = new MemoAdapter(memotexts);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));









        return  v;
    }


    }

