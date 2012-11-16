package msgame;

public class Main {
	/** Ticks per second */
	public static final int TPS = 60;
	/** Frames per second */
	public static final int FPS = 60;

	public Main() {
		initDisplay();
		initScene();
		startLoop();
		clean();
	}

	public void initDisplay() {
	}

	public void initScene() {
	}

	public void startLoop() {
		long lastTime = System.currentTimeMillis();
		double unprocessed = 0;

		for (;;) {
			long now = System.currentTimeMillis();
			long delta = (now - lastTime);
			lastTime = now;
			unprocessed += delta / (1000.0 / TPS);

			boolean ticked = false;
			while (unprocessed > 1) {
				ticked = true;
				tick();
				unprocessed--;
			}
			if (ticked) {
				render();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void render() {

	}

	public void tick() {

	}

	public void clean() {
	}

	public static void main(String[] args) {
		new Main();
	}
}
