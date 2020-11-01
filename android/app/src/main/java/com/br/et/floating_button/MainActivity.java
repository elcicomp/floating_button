package com.br.et.floating_button;

import com.yhao.floatwindow.FloatWindow;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "floating_button";


    //    MethodChannel channel = new MethodChannel(getFlutterView(), CHANNEL);
    MethodChannel channel = new  MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL);

        channel.setMethodCallHandler(
                (call, result) -> {
        switch (call.method) {
            case "create":
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.plus);

                FloatWindow.with(getApplicationContext()).setView(imageView)
                        .setWidth(Screen.width, ratio:0.15f)
                        .setHeigth(Screen.width, ratio:0.15f)
                        .setX(Screen.width, ratio:0.8f)
                        .setY(Screen.height, ratio:0.3f)
						.setDesktopShow(true)
                    .build();
                imageView.setOnClickListener(v -> {
                    channel.invokeMethod(method: "touch", arguments:null);
                });

                break;
            case "show":
                FloatWindow.get().show();

                break;
            case "hide":
                FloatWindow.get().hide();
                break;
            case "isShowing":
                result.success(FloatWindow.get().isShowing());
                break;
            default:
                result.notImplemented();
        }
    }
        );
}
@Override
    protect void onDestroy(){
            FloatWindow.destroy();
            super.onDestroy();
            }
            }
