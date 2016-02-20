package project01.csc296.project01;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreBoardFragment extends Fragment {

    public TextView mP1Score, mP2Score, mP1Name, mP2Name;
    public String P1Name, P2Name;
    public int P1Score, P2Score;

    public static final String KEY_P1Name = "project01.csc296.P1Name";
    public static final String KEY_P2Name = "project01.csc296.P2Name";
    public String KEY_P1Score = "Player1Score";
    public String KEY_P2Score = "Player2Score";


    public ScoreBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutInflater lf = getActivity().getLayoutInflater();

        View v = lf.inflate(R.layout.fragment_score_board, container, false);

        Bundle extras = getArguments();

        P1Name = extras.getString(KEY_P1Name);
        P2Name = extras.getString(KEY_P2Name);
        P1Score = extras.getInt(KEY_P1Score);
        P2Score = extras.getInt(KEY_P2Score);

        Log.i("IN FRAG", "Player scores: " + P1Score + ", " + P2Score);

        mP1Score = (TextView) v.getRootView().findViewById(R.id.TextView_FragP1Score);

        mP2Score = (TextView) v.getRootView().findViewById(R.id.TextView_FragP2Score);

        mP1Name = (TextView) v.getRootView().findViewById(R.id.TextView_FragP1Name);

        mP2Name = (TextView) v.getRootView().findViewById(R.id.TextView_FragP2Name);

        mP1Name.setText(P1Name);
        mP2Name.setText(P2Name);

        mP1Score.setText(Integer.toString(P1Score));

        mP2Score.setText(Integer.toString(P2Score));

        return v;
    }


}
