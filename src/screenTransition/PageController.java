package screenTransition;

/**
 * 一つのPageに対応するController.
 * Pageに共通する機能を持たせたい。
 *
 * @author kana
 */
public interface PageController extends Controller {

	public abstract void init();

}
