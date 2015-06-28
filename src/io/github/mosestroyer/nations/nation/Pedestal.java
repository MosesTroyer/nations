package io.github.mosestroyer.nations.nation;

public class Pedestal {
	
	private String name;
	private int position;
	private int x;
	private int y;
	private int z;
	private String flag;
	
	public Pedestal(String name, int position, int x, int y, int z, String flag){
		setName(name);
		setPosition(position);
		setX(x);
		setY(y);
		setZ(z);
		setFlag(flag);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getZ() {
		return z;
	}
	
	public void setZ(int z) {
		this.z = z;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}

}