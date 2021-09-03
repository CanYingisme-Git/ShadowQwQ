package al.nya.shadowqwq.webhook;

import al.nya.shadowqwq.utils.github.WebHook;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.Mirai;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class WebHookHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Bot.getInstances().forEach(b ->{
            b.getGroups().forEach(g ->{
                if (g.getId() == 943177546){
                    try {
                        List<String> event = exchange.getResponseHeaders().get("X-github-event");
                        String eventS = event.get(0);
                        Gson gson = new Gson();

                        WebHook webHook = gson.fromJson(new String(readInputStream(exchange.getRequestBody())),WebHook.class);
                        StringBuilder sb = new StringBuilder();
                        sb.append("您订阅的github库 ").append(webHook.getRepository().getFull_name()).append("有了新的事件:").append(eventS).append("\n");
                        sb.append("分支:").append(webHook.getRef().replace("refs/heads/","")).append("\n");
                        if (eventS.equalsIgnoreCase("push")){
                            sb.append("提交者:").append(webHook.getPusher().getName()).append("<").append(webHook.getPusher().getEmail()).append(">");
                            webHook.getCommits().forEach(C ->{
                                sb.append("\n").append(C.getCommitter().getUsername()).append(":").append(C.getMessage());
                            });
                        }
                        g.sendMessage(sb.toString());
                    }catch (Exception e){
                        g.sendMessage(e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        });
        byte[] responseContentByte = new byte[0];

        exchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");

        //设置响应码和响应体长度，必须在getResponseBody方法之前调用！
        exchange.sendResponseHeaders(200, 0);

        OutputStream out = exchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }
    public byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
