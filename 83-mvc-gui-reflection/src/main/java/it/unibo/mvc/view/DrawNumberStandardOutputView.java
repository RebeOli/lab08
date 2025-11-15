import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStandardOutputView implements DrawNumberView {
    @Override
    public void setController(DrawNumberController observer) {
        observer.addView(this);
    }

    @Override
    public void start() {
        this.frame.setVisible(true);
    }

    @Override
    public void result(DrawResult res) {
        System.out.println(res.getDeclaringClass());
    }
}