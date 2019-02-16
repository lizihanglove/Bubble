package site.lizihanglove.example;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import site.lizihanglove.bubble.Bubble;

public class MainActivity extends AppCompatActivity {
    private boolean switchs = false;
    private Bubble bubble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bubble = findViewById(R.id.bubble);
    }

    public void change(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0.3f, 1f);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                bubble.setOffset(animatedValue);
                bubble.setBubbleColor(Color.parseColor("#f07810"));
            }
        });
        animator.start();
    }
}
