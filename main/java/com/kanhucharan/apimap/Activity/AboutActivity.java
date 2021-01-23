package com.kanhucharan.apimap.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.kanhucharan.apimap.R;

public class AboutActivity extends AppCompatActivity {

    private ImageView featureGraphicImageView;

    private ImageButton shareImageButton;
    private ImageButton rateUsImageButton;
    private ImageButton feedbackImageButton;

    private CardView sourceCodeOnGitHubCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("About");

        featureGraphicImageView = (ImageView) findViewById(R.id.image_view_feature_graphic_about);
        featureGraphicImageView.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels;
        featureGraphicImageView.getLayoutParams().height = (int) ((double) getResources().getDisplayMetrics().widthPixels * (500.0 / 1024.0));

        shareImageButton = (ImageButton) findViewById(R.id.image_button_share_about);
        rateUsImageButton = (ImageButton) findViewById(R.id.image_button_rate_us_about);
        feedbackImageButton = (ImageButton) findViewById(R.id.image_button_feedback_about);

        sourceCodeOnGitHubCardView = (CardView) findViewById(R.id.card_view_source_code_on_github);

        loadActivity();

    }

    private void loadActivity() {

        shareImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                String packageName = "com.kanhucharan.quizfun";
                Intent appShareIntent = new Intent(Intent.ACTION_SEND);
                appShareIntent.setType("text/plain");
                String extraText = "Hey! Check out this amazing app.\n";
                extraText += "https://play.google.com/store/apps/details?id=" + packageName;
                appShareIntent.putExtra(Intent.EXTRA_TEXT, extraText);
                startActivity(appShareIntent);
            }
        });

        rateUsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                String packageName = "com.kanhucharan.quizfun";
                String playStoreLink = "https://play.google.com/store/apps/details?id=" + packageName;
                Intent appRateUsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(playStoreLink));
                startActivity(appRateUsIntent);
            }
        });

        feedbackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent feedbackIntent = new Intent(Intent.ACTION_SENDTO);
                feedbackIntent.setData(Uri.parse("mailto:"));
                feedbackIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kanhucharanar@gmail.com"});
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback: " + getResources().getString(R.string.app_name));
                startActivity(feedbackIntent);
            }
        });

        sourceCodeOnGitHubCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                String githubLink = "https://github.com/hitanshu-dhawan/" + getResources().getString(R.string.app_name);
                Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubLink));
                startActivity(githubIntent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
