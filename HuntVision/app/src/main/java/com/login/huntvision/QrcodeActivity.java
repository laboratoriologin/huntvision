package com.login.huntvision;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Toast;


import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.Cliente;
import com.login.huntvision.models.Item;
import com.login.huntvision.models.ItemLocal;
import com.login.huntvision.models.Local;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class QrcodeActivity extends DefaultActivity implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;


    //QRCode objects

    private Item item;
    private Local local;
    private Cliente cliente;
    private ItemLocal itemLocal;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this) {
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
        List<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.QRCODE);
        mScannerView.setFormats(formats);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        String contents = rawResult.getContents();

        if (contents != null) {

            String chave = contents.split("_")[0];

            String localId = contents.split("_")[1];

            QueryBuilder<Item, String> builder = getHelper().getItemRuntimeDAO().queryBuilder();

            try {

                builder.where().eq("chave", chave);

                item = getHelper().getItemRuntimeDAO().queryForFirst(builder.prepare());

                if (item != null) {

                    QueryBuilder<Local, String> builderLocal = getHelper().getLocalRuntimeDAO().queryBuilder();

                    builderLocal.where().eq("id", localId);

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
        public static final String TRADE_MARK_TEXT = "HuntVision";
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

            BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher);

            canvas.drawBitmap(drawable.getBitmap(), tradeMarkLeft, tradeMarkTop, PAINT);
        }
    }
}