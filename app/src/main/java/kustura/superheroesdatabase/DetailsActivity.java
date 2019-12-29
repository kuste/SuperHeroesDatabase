package kustura.superheroesdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import kustura.superheroesdatabase.apiServiceDto.Result;


public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout_row);

        Intent intent = getIntent();
        Result result = (Result) intent.getSerializableExtra("Result");

        PopulatePowerStats(result);
        PopulateInfo(result);

    }


    private void PopulatePowerStats(Result result) {
        if (result != null) {
            TextView str = findViewById(R.id.str);
            str.setText(result.getPowerstats().getStrength());
            TextView inte = findViewById(R.id.intel);
            inte.setText(result.getPowerstats().getIntelligence());
            TextView speed = findViewById(R.id.spe);
            speed.setText(result.getPowerstats().getSpeed());
            TextView dur = findViewById(R.id.dur);
            dur.setText(result.getPowerstats().getDurability());
            TextView pow = findViewById(R.id.pow);
            pow.setText(result.getPowerstats().getPower());
            TextView com = findViewById(R.id.com);
            com.setText(result.getPowerstats().getCombat());
        }
    }

    private void PopulateInfo(Result result) {
        if (result != null) {

            TextView gender = findViewById(R.id.gender);
            gender.setText(result.getAppearance().getGender());
            TextView race = findViewById(R.id.race);
            race.setText(result.getAppearance().getRace());
            TextView height = findViewById(R.id.height);
            height.setText(result.getAppearance().getHeight().get(1));
            TextView weight = findViewById(R.id.weight);
            weight.setText(result.getAppearance().getWeight().get(1));
            TextView alignment = findViewById(R.id.alignment);
            alignment.setText(result.getBiography().getAlignment());
            TextView base = findViewById(R.id.base);
            base.setText(result.getWork().getBase());
            List<String> tempList = result.getBiography().getAliases();
            LinearLayout layout = findViewById(R.id.alias_layout);
            for (String s : tempList) {
                TextView view = new TextView(this);
                view.setText(s);
                layout.addView(view);
            }

        }
    }


}

