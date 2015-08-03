package login.com.huntvision;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Toast;

import com.google.zxing.Result;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Item;
import login.com.huntvision.models.ItemLocal;
import login.com.huntvision.models.Local;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrcodeActivity extends DefaultActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;


    //QRCode objects

    private Item item;
    private Local local;
    private Cliente cliente;
    private ItemLocal itemLocal;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return new CustomViewFinderView(context);
            }
        };
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        String contents = rawResult.getText();

        if (contents != null) {

            QueryBuilder<Item, String> builder = getHelper().getItemRuntimeDAO().queryBuilder();

            try {

                builder.where().eq("chave", contents.toString());

                item = getHelper().getItemRuntimeDAO().queryForFirst(builder.prepare());

                if (item != null) {

                    QueryBuilder<ItemLocal, String> builderItemLocal = getHelper().getItemLocalRuntimeDAO().queryBuilder();

                    builderItemLocal.where().eq("item_id", item.getId().toString());

                    itemLocal = getHelper().getItemLocalRuntimeDAO().queryForFirst(builderItemLocal.prepare());

                    QueryBuilder<Local, String> builderLocal = getHelper().getLocalRuntimeDAO().queryBuilder();

                    builderLocal.where().eq("id", itemLocal.getLocal_id().toString());

                    local = getHelper().getLocalRuntimeDAO().queryForFirst(builderLocal.prepare());

                    QueryBuilder<Cliente, String> builderCliente = getHelper().getClienteRuntimeDAO().queryBuilder();

                    builderCliente.where().eq("id", local.getClienteId().toString());

                    cliente = getHelper().getClienteRuntimeDAO().queryForFirst(builderCliente.prepare());

                    Intent mainIntent = new Intent(this, QuestionarioActivity_.class);

                    mainIntent.putExtra("item", item);

                    mainIntent.putExtra("cliente", cliente);

                    mainIntent.putExtra("local", local);

                    this.startActivity(mainIntent);

                    finish();


                } else {

                    Toast.makeText(this, "Não existe vistoria para esse QRCode, obrigado.", Toast.LENGTH_SHORT).show();

                    finish();

                }

            } catch (SQLException e) {

                Toast.makeText(this, "Ocorreu um erro ao ler código, tente novamente.", Toast.LENGTH_SHORT).show();

                e.printStackTrace();

                finish();

            }


        }
    }

    private static class CustomViewFinderView extends ViewFinderView {
        public static final String TRADE_MARK_TEXT = "ZXing";
        public static final int TRADE_MARK_TEXT_SIZE_SP = 40;
        public final Paint PAINT = new Paint();

        public CustomViewFinderView(Context context) {
            super(context);
            init();
        }

        public CustomViewFinderView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            PAINT.setColor(Color.WHITE);
            PAINT.setAntiAlias(true);
            float textPixelSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    TRADE_MARK_TEXT_SIZE_SP, getResources().getDisplayMetrics());
            PAINT.setTextSize(textPixelSize);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawTradeMark(canvas);
        }

        private void drawTradeMark(Canvas canvas) {
            Rect framingRect = getFramingRect();
            float tradeMarkTop;
            float tradeMarkLeft;
            if (framingRect != null) {
                tradeMarkTop = framingRect.bottom + PAINT.getTextSize() + 10;
                tradeMarkLeft = framingRect.left;
            } else {
                tradeMarkTop = 10;
                tradeMarkLeft = canvas.getHeight() - PAINT.getTextSize() - 10;
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, PAINT);
        }
    }
}