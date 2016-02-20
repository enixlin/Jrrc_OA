package enixlin.jrrc.OA.client.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.BasicEofSensorWatcher;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import com.gdbeim.oa.applet.HttpGet;

public class Login extends Thread{
	private URI uri;
	private String result;

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String submit(URI uri) {
		//建立HTTP连接	
		CloseableHttpClient client=HttpClients.createDefault();
		

		HttpPost httpPost=new HttpPost(uri.toString());
		try {
			CloseableHttpResponse response=client.execute(httpPost);
			HttpEntity entity=response.getEntity();
			if (entity!=null) {
				InputStream inputStream=entity.getContent();
				byte[] b=new byte[1024];
				BufferedReader bufferedReader=new BufferedReader( new InputStreamReader(inputStream,"utf-8"));
				String line;
				while((line=bufferedReader.readLine())!=null){
					result=result+line;
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public void run() {
		System.out.println(submit(uri));
	}


}
