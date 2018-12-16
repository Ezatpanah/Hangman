package ir.rojadev.game.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_finish);

    Button btn_exit = (Button) findViewById(R.id.btn_exit);
    Button btn_restart = (Button) findViewById(R.id.btn_restart);

    btn_exit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    btn_restart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(FinishActivity.this, MainActivity.class);
        FinishActivity.this.startActivity(intent);
        finish();
      }
    });

    TextView txt_result = (TextView) findViewById(R.id.txt_result);
    Intent intent = getIntent();
    String result = intent.getStringExtra("RESULT");

    if (result.equals("WON")) {
      txt_result.setText("You Won!");
    } else {
      txt_result.setText("Game Over!");
    }
  }
}
