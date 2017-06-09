package monitor.model;



import monitor.view.View;

public interface Model {

	public void addView(View view);
	public boolean removeView(View view);
	//public void update(Packet packet);
}
