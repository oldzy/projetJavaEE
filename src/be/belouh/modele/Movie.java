package be.belouh.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Movie {
	private static String URL1 = "https://tv-v2.api-fetch.website/";
	private static String URL2 = "https://tv.api-fetch.website/";
	private static String URL3 = "https://api-fetch.website/tv/";
	private static String URL_DOWNLOAD = "http://qlf.ddns.net/add_torrent.php?url=";
	private static String URL_STATUS = "http://qlf.ddns.net/get_torrent_status.php?id=";

	public Movie() {

	}

	public HashMap<String, String> getMovie(String url, String id) {
		HashMap<String, String> film;
		try {
			URL obj = new URL(url + "movie/" + id);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

			film = new HashMap<String, String>();

			String genres = "";
			String magnetLang = jsonObject.get("torrents").toString().split("\":")[0].split("\"")[1];
			String magnetQualite = "";
			if (((JSONObject) ((JSONObject) jsonObject.get("torrents")).get(magnetLang)).get("720p") != null)
				magnetQualite = "720p";
			else
				magnetQualite = "1080p";

			film.put("id", new String(((String) jsonObject.get("imdb_id")).getBytes(), "UTF-8"));
			film.put("imdb_page", "http://www.imdb.com/title/" + film.get("id").toString());
			film.put("title", new String(((String) jsonObject.get("title")).getBytes(), "UTF-8"));
			film.put("year", new String(((String) jsonObject.get("year")).getBytes(), "UTF-8"));
			film.put("synopsis", new String(((String) jsonObject.get("synopsis")).getBytes(), "UTF-8"));
			film.put("runtime", new String(((String) jsonObject.get("runtime")).getBytes(), "UTF-8"));
			film.put("magnet", new String(
					((String) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("torrents")).get(magnetLang))
							.get(magnetQualite)).get("url")).getBytes(),
					"UTF-8"));
			for (Object genre : (JSONArray) jsonObject.get("genres")) {
				genres += (String) genre + " / ";
			}
			film.put("genres", genres);
			film.put("poster",
					new String(((String) ((JSONObject) jsonObject.get("images")).get("poster")).getBytes(), "UTF-8"));
			film.put("fanart",
					new String(((String) ((JSONObject) jsonObject.get("images")).get("fanart")).getBytes(), "UTF-8"));
			film.put("note", ((JSONObject) jsonObject.get("rating")).get("percentage").toString());
		} catch (MalformedURLException e) {
			film = null;
		} catch (IOException e) {
			film = null;
		} catch (ParseException e) {
			film = null;
		}
		return film;
	}

	public ArrayList<HashMap<String, String>> getPage(String url, int n) {
		ArrayList<HashMap<String, String>> list;
		try {
			URL obj = new URL(url + "movies/" + n + "?sort=trending");
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONParser parser = new JSONParser();

			JSONArray jsonArray = (JSONArray) parser.parse(response.toString());
			list = new ArrayList<HashMap<String, String>>();
			for (Object film : jsonArray) {
				HashMap<String, String> h = getMovie(url,
						new String(((String) ((JSONObject) film).get("imdb_id")).getBytes(), "UTF-8"));
				list.add(h);
			}
		} catch (MalformedURLException e) {
			list = null;
		} catch (IOException e) {
			list = null;
		} catch (ParseException e) {
			list = null;
		}
		return list;
	}

	public int getPages(String url) {
		int res;
		try {
			URL obj = new URL(url + "movies");
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONParser parser = new JSONParser();

			JSONArray jsonArray = (JSONArray) parser.parse(response.toString());
			res = jsonArray.size();
		} catch (MalformedURLException e) {
			res = 0;
		} catch (IOException e) {
			res = 0;
		} catch (ParseException e) {
			res = 0;
		}
		return res;
	}

	public String getUrl() {
		String res;
		try {
			URL obj = new URL(URL1 + "status");
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			if (con.getResponseCode() == 200)
				res = URL1;
			else {
				obj = new URL(URL2 + "status");
				con = (HttpsURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				if (con.getResponseCode() == 200)
					res = URL2;
				else {
					obj = new URL(URL3 + "status");
					con = (HttpsURLConnection) obj.openConnection();
					con.setRequestMethod("GET");
					con.setRequestProperty("User-Agent", "Mozilla/5.0");
					if (con.getResponseCode() == 200)
						res = URL3;
					else
						res = null;
				}
			}
		} catch (MalformedURLException e) {
			res = null;
		} catch (IOException e) {
			res = null;
		}
		return res;
	}

	public String startDownload(String url) {
		String res;
		try {
			URL obj = new URL(URL_DOWNLOAD + url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				res = response.toString();
			} else {
				res = null;
			}
		} catch (MalformedURLException e) {
			res = null;
		} catch (IOException e) {
			res = null;
		}
		return res;
	}
	
	public String getStatus(String id) {
		String res;
		try {
			URL obj = new URL(URL_STATUS + id);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				res = response.toString();
			} else {
				res = null;
			}
		} catch (MalformedURLException e) {
			res = null;
		} catch (IOException e) {
			res = null;
		}
		return res;
	}
}
