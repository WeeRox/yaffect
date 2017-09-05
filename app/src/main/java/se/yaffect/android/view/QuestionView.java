package se.yaffect.android.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import se.yaffect.android.R;

public class QuestionView extends LinearLayoutCompat {

    private ImageView buttonLike;
    private TextView textTimeAsked;
    private TextView textTimeAnswered;
    private TextView textQuestion;
    private TextView textAnswer;

    private boolean liked = false;

    public QuestionView(Context context) {
        super(context);
        initializeView(context);
    }

    public QuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public QuestionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_question, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        buttonLike = (ImageView) this.findViewById(R.id.button_like);
        textTimeAsked = (TextView) this.findViewById(R.id.text_time_asked);
        textTimeAnswered = (TextView) this.findViewById(R.id.text_time_answered);
        textQuestion = (TextView) this.findViewById(R.id.text_question);
        textAnswer = (TextView) this.findViewById(R.id.text_answer);
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        if (this.liked != liked) {
            this.liked = liked;

            if (liked) {
                buttonLike.setImageResource(R.drawable.ic_like);
            } else {
                buttonLike.setImageResource(R.drawable.ic_like_outline);
            }
        }
    }
}
