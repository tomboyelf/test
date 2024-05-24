package tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//フロントコントローラーのサーブレットを、末尾が.actionで終わるURLに対応つける
//つまり、Search.actionのようなURLを開くと、フロントコントローラーが実行される
@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
//			substringメソッド
//			引数番目の文字から末尾までを文字列として返す
			String path = request.getServletPath().substring(1);
//			上で取得した文字列の中身を部分的に入れ替え
			String name = path.replace(".a", "A").replace('/', '.');
//			指定した名前のクラスまたはインターフェースに関連つけられた、Classオブジェクトを返す
//			つまり、クラス名を指定したクラスのインスタンスを生成できる
			Action action = (Action) Class.forName(name).newInstance();
			String url = action.execute(request, response);
			request.getRequestDispatcher(url).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

//	こうすることで、doGetリクエストを受け取った場合でもdoPostメソッドが呼び出される
	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
