package com.fintechhackathon14.paybus.qrscanner.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.fintechhackathon14.paybus.databinding.FragmentQrScannerBinding;
import com.fintechhackathon14.paybus.qrscanner.presenter.QrScannerPresenter;
import com.fintechhackathon14.paybus.qrscanner.presenter.QrScannerPresenterContract;
import com.google.zxing.Result;

import org.jetbrains.annotations.NotNull;

public class QrScannerViewFragment extends Fragment implements QrScannerViewContract {

    private FragmentQrScannerBinding binding;
    private CodeScanner mCodeScanner;
    private Activity activity;
    private QrScannerPresenterContract qrScannerPresenterContract;

    final int CAMERA_PERM = 1;

    private boolean isCameraPermission = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQrScannerBinding.inflate(inflater, container, false);
        activity = getActivity();
        qrScannerPresenterContract = new QrScannerPresenter(this, getContext(), getActivity());

        mCodeScanner = new CodeScanner(activity, binding.scannerView);

        askPermission();
        if (isCameraPermission) {
            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull final Result result) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            qrScannerPresenterContract.parseStringFromQr(result.getText());
                        }
                    });
                }
            });
            binding.scannerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCodeScanner.startPreview();
                }
            });
        }

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private void askPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM);
        } else {
            mCodeScanner.startPreview();
            isCameraPermission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {

        if (requestCode == CAMERA_PERM){


            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                mCodeScanner.startPreview();
                isCameraPermission = true;
            }else {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.CAMERA)){

                    new AlertDialog.Builder(activity)
                            .setTitle("Разрешение")
                            .setMessage("Доступ к камере нужен, чтобы приложение мошло сканировать Qr-коды")
                            .setPositiveButton("Продолжить", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},CAMERA_PERM);

                                }
                            }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    }).create().show();

                }else {

                    new AlertDialog.Builder(getContext())
                            .setTitle("Разрешение")
                            .setMessage("Вы запретили использование камеры. Мы не можем считать QR-код. Необходимо разрешение на использование камеры [Настройки] > [Разрешения]")
                            .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package",activity.getPackageName(),null));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    activity.finish();


                                }
                            }).setNegativeButton("Нет, закрыть приложение", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                            activity.finish();

                        }
                    }).create().show();



                }

            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showError(String textError) {
        Toast.makeText(getContext(), textError, Toast.LENGTH_LONG).show();
    }
}