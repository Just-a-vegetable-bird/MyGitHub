package com.hncu.taoshu.bean;

public class Room {
	private int roomId;
	private String roomName;
	private String roomPic;
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomName=" + roomName
				+ ", roomPic=" + roomPic + "]";
	}
	public Room(int roomId, String roomName, String roomPic) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomPic = roomPic;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomPic() {
		return roomPic;
	}
	public void setRoomPic(String roomPic) {
		this.roomPic = roomPic;
	}
	public Room() {
		// TODO Auto-generated constructor stub
	}
}
