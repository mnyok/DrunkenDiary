package tourplatform.drunkdiary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tourplatform.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-16.
 */
public class StatsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        return view;
    }
}
