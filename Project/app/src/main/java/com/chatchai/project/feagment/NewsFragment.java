package com.chatchai.project.feagment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.chatchai.project.R;
import com.chatchai.project.ShowNewsActivity;
import com.chatchai.project.adapter.AdapterNews;
import com.chatchai.project.model.ModelNews;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    private AdapterNews adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        final ArrayList<ModelNews> modelNewses = new ArrayList<ModelNews>();

        ModelNews list = new ModelNews();
        list.setNewsName("ความสำคัญของการเลี้ยงลูกด้วยแม่");
        list.setNewsDetail("ผลดีต่อลูก\n" +
                "1.\tได้รับสารอาหารควบถ้วน และพอเหมาะแก้ความต้องการของลูก ไม่ทำให้ลูกเป็นโรคขาดสารอาหารหรือโรคอ้วน\n" +
                "2.\tได้รับสารอื่นนอกเหนือไปจากสารอาหาร เช่น ฮอร์โมน ที่จะช่วยในการเจริญเติบโตของร่างกาย และการทำงานของอวัยวะบางระบบ\n" +
                "3.\tลดอัตราการเกิดโรคอุจจาระร่วง\n" +
                "4.\tได้รับภูมิคุ้มกันโรคและสารต่อด้านเชื้อโรค\n" +
                "ผลดีต่อแม่\n" +
                "1.\tทำให้มดลูกหดตัวดี เข้าอู่เร็วรับน้ำคาวปลาได้ดี        \n" +
                "2.\tทำให้แม่มีความรู้สึกผูกพันกับลูกมากขึ้น\n" +
                "3.\tไม่เสียเวลา เพราะนมแม่มีพร้อมที่จะให้อยู่เสมอ\n" +
                "4.\tประหนัดรายจ่ายของครอบครัว\n");
        modelNewses.add(list);

        ModelNews list2 = new ModelNews();
        list2.setNewsName("การป้องกันการแพร่เชื้อ เอช ไอ วี จากแม่สู่ลูก");
        list2.setNewsDetail("การแพร่เชื้อ เอส ไอ วี เกิดขึ้นได้ 3 ระยะ\n" +
                "1.\tระยะตั้งครรภ์ ขณะที่เด็กอยู่ในท้องแม่ เชื้อเอช ไอ วี จะจ่ายจากแม่ทางรก\n" +
                "2.\tระยะคลอด จากการที่เด็กสัมผัสเลือกจำนวนมาก สัมผัสน้ำคร่ำและสารคัดหลั่งในช่องคลอดของแม่\n" +
                "3.\tระยะหลังคลอด เกิดจากกสรที่เด็กได้รับนมจากแม่\n" +
                "การป้องกันการแพร่เชื้อ เอส ไอ วี จากแม่สู่ลูก\n" +
                "1.\tรับประทานยาต้านไวรัสตามแพทย์สั่ง\n" +
                "2.\tงดเลื้ยงลูกด้วยนมแม่\n" +
                "3.\tมาตรวจตามนัดทุกครั้ง\n");
        modelNewses.add(list2);

        ModelNews list3 = new ModelNews();
        list3.setNewsName("คุณลักษณะที่ดีของแม่เพื่อให้ลูกมีสุขภาพดี");
        list3.setNewsDetail("1.\tมีลูกคนแรกเมื่ออายุระหว่าง 20 -30 ปี\n" +
                "2.\tท้องว่างอย่างน้อย 2 ปี\n" +
                "3.\tมีลูกไม่เกิน 2 คน\n" +
                "4.\tฉีดวัคซีนป้องกันโรคบาดทะยักครบ 2 เข็ม ระหว่างท้อง\n" +
                "5.\tลูกมีน้ำหนักแรกเกิด มากกว่า 2,000 กรัมขึ้นไป\n" +
                "6.\tนำลูกทุกคนไปรับวัคซีนป้องกันโรคตามที่เจ้าหน้าที่สาธารณสุขกำหนด\n");
        modelNewses.add(list3);


        final int[] image = {R.drawable.news1, R.drawable.news2, R.drawable.news3};

        adapter = new AdapterNews(getActivity(), modelNewses,image);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterNews.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ShowNewsActivity.class);
                intent.putExtra("title", modelNewses.get(position).getNewsName());
                intent.putExtra("detail", modelNewses.get(position).getNewsDetail());
                intent.putExtra("image", image[position]);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


        return view;
    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }


}
