package tourplatform.drunkdiary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import tourplatform.drunkdiary.Alcohol;
import tourplatform.drunkdiary.Condition;
import tourplatform.drunkdiary.DiaryAdapter;
import tourplatform.drunkdiary.DiaryData;
import tourplatform.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-05.
 */
public class DiaryFragment extends Fragment {
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        listView = (ListView) view.findViewById(R.id.diary_list);

        DiaryAdapter adapter = new DiaryAdapter(getActivity());
        adapter.add(new DiaryData("2015.12.31 FRI", Condition.DEAD, Alcohol.LIQUOR, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁ"));
        adapter.add(new DiaryData("2015.12.30 THU", Condition.SOSO, Alcohol.SOMAC, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁasdasdasdasddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        adapter.add(new DiaryData("2015.12.28 MON", Condition.VOMIT, Alcohol.BEER, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁ"));
        adapter.add(new DiaryData("2015.12.31 FRI", Condition.DEAD, Alcohol.MAKGEOLLI, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁ"));
        adapter.add(new DiaryData("2015.12.30 THU", Condition.SOSO, Alcohol.SOMAC, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁasdasdasdasddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"));
        adapter.add(new DiaryData("2015.12.28 MON", Condition.VOMIT, Alcohol.BEER, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁ"));
        adapter.add(new DiaryData("2015.12.31 FRI", Condition.DEAD, Alcohol.LIQUOR, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁ"));
        adapter.add(new DiaryData("2015.12.30 THU", Condition.SOSO, Alcohol.SOMAC, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁasdasdasdasddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        adapter.add(new DiaryData("2015.12.28 MON", Condition.VOMIT, Alcohol.BEER, "어제 친구들이랑 ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁ"));
        listView.setAdapter(adapter);

        return view;
    }
}
