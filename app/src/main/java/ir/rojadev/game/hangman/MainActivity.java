package ir.rojadev.game.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private String wordDashed = "";
  private String selectedWord = "";
  private int failCount = 0;
  private TextView txt_word;
  private ImageView img_face;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // define java pointers to ui elements
    img_face = (ImageView) findViewById(R.id.img_face);
    txt_word = (TextView) findViewById(R.id.txt_word);
    final TextView txt_a = (TextView) findViewById(R.id.txt_a);
    final TextView txt_b = (TextView) findViewById(R.id.txt_b);
    final TextView txt_c = (TextView) findViewById(R.id.txt_c);
    final TextView txt_d = (TextView) findViewById(R.id.txt_d);
    final TextView txt_e = (TextView) findViewById(R.id.txt_e);
    final TextView txt_f = (TextView) findViewById(R.id.txt_f);
    final TextView txt_g = (TextView) findViewById(R.id.txt_g);
    final TextView txt_h = (TextView) findViewById(R.id.txt_h);
    final TextView txt_i = (TextView) findViewById(R.id.txt_i);
    final TextView txt_j = (TextView) findViewById(R.id.txt_j);
    final TextView txt_k = (TextView) findViewById(R.id.txt_k);
    final TextView txt_l = (TextView) findViewById(R.id.txt_l);
    final TextView txt_m = (TextView) findViewById(R.id.txt_m);
    final TextView txt_n = (TextView) findViewById(R.id.txt_n);
    final TextView txt_o = (TextView) findViewById(R.id.txt_o);
    final TextView txt_p = (TextView) findViewById(R.id.txt_p);
    final TextView txt_q = (TextView) findViewById(R.id.txt_q);
    final TextView txt_r = (TextView) findViewById(R.id.txt_r);
    final TextView txt_s = (TextView) findViewById(R.id.txt_s);
    final TextView txt_t = (TextView) findViewById(R.id.txt_t);
    final TextView txt_u = (TextView) findViewById(R.id.txt_u);
    final TextView txt_v = (TextView) findViewById(R.id.txt_v);
    final TextView txt_w = (TextView) findViewById(R.id.txt_w);
    final TextView txt_x = (TextView) findViewById(R.id.txt_x);
    final TextView txt_y = (TextView) findViewById(R.id.txt_y);
    final TextView txt_z = (TextView) findViewById(R.id.txt_z);

    // select word and store dashed version
    selectedWord = selectWord();
    wordDashed = getDashWord(selectedWord);

    // initialize ui
    img_face.setImageResource(R.drawable.face_1);
    txt_word.setText(wordDashed);

    // define general virtual keyboard click listener
    View.OnClickListener listener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        processKey(view);
      }
    };

    // assign common listener to ui virtual keyboard
    txt_a.setOnClickListener(listener);
    txt_b.setOnClickListener(listener);
    txt_c.setOnClickListener(listener);
    txt_d.setOnClickListener(listener);
    txt_e.setOnClickListener(listener);
    txt_f.setOnClickListener(listener);
    txt_g.setOnClickListener(listener);
    txt_h.setOnClickListener(listener);
    txt_i.setOnClickListener(listener);
    txt_j.setOnClickListener(listener);
    txt_k.setOnClickListener(listener);
    txt_l.setOnClickListener(listener);
    txt_m.setOnClickListener(listener);
    txt_n.setOnClickListener(listener);
    txt_o.setOnClickListener(listener);
    txt_p.setOnClickListener(listener);
    txt_q.setOnClickListener(listener);
    txt_r.setOnClickListener(listener);
    txt_s.setOnClickListener(listener);
    txt_t.setOnClickListener(listener);
    txt_u.setOnClickListener(listener);
    txt_v.setOnClickListener(listener);
    txt_w.setOnClickListener(listener);
    txt_x.setOnClickListener(listener);
    txt_y.setOnClickListener(listener);
    txt_z.setOnClickListener(listener);
  }

  private String selectWord() {
    String[] words = {
      "Object Oriented",
      "Polymorphism",
      "Encapsulation",
      "Interface",
      "Composition",
      "Android",
      "Development",
      "Programming",
      "Design",
      "Research",
      "Google",
      "Refactoring",
      "Renaming",
      "Documentation",
    };
    int randomIndex = (int) (Math.random() * words.length);
    return words[randomIndex];
  }

  private String getDashWord(String word){
    String dashed = "";
    for (int i = 0; i< word.length(); i++) {
      if (word.charAt(i) != ' ') {
        dashed += "-";
      } else {
        dashed += " ";
      }
    }

    return dashed;
  }

  private String getIdAsString(View view) {
    String id = view.getResources().getResourceEntryName(view.getId());
    return id;
  }

  private void lostGame() {
    Intent intent = new Intent(MainActivity.this, FinishActivity.class);
    intent.putExtra("RESULT", "LOST");
    MainActivity.this.startActivity(intent);
    finish();
  }

  private void winGame() {
    Intent intent = new Intent(MainActivity.this, FinishActivity.class);
    intent.putExtra("RESULT", "WON");
    MainActivity.this.startActivity(intent);
    finish();
  }

  private String replaceChar(String target, int index, char newChar) {
    char[] charArray = target.toCharArray();
    charArray[index] = newChar;
    return new String(charArray);
  }

  private void processKey(View view) {
    TextView textView = (TextView) view;
    String id = getIdAsString(textView);
    String letter = id.replace("txt_", "");
    char letterChar = letter.charAt(0);

    String wordLowercase = selectedWord.toLowerCase();
    if (wordLowercase.contains(letter)) {
      for (int i=0; i<wordLowercase.length(); i++) {
        if (wordLowercase.charAt(i) == letterChar) {
          wordDashed = replaceChar(wordDashed, i, selectedWord.charAt(i));
          txt_word.setText(wordDashed);
          if (!wordDashed.contains("-")) {
            winGame();
            return;
          }
        }
      }
    } else {
      failCount++;
      int imageId = R.drawable.face_1;

      if (failCount >= 8) {
        img_face.setImageResource(R.drawable.face_9);
        lostGame();
        return;
      }

      switch (failCount) {
        case 1: imageId = R.drawable.face_2; break;
        case 2: imageId = R.drawable.face_3; break;
        case 3: imageId = R.drawable.face_4; break;
        case 4: imageId = R.drawable.face_5; break;
        case 5: imageId = R.drawable.face_6; break;
        case 6: imageId = R.drawable.face_7; break;
        case 7: imageId = R.drawable.face_8; break;
      }
      img_face.setImageResource(imageId);
    }
    textView.setVisibility(View.INVISIBLE);
  }
}
