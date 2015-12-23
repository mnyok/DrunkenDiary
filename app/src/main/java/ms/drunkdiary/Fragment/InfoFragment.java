package ms.drunkdiary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ms.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-23.
 */
public class InfoFragment  extends Fragment implements View.OnClickListener{

    ImageButton bt_close;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        bt_close = (ImageButton) view.findViewById(R.id.info_bt_close);
        bt_close.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.info_bt_close:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
