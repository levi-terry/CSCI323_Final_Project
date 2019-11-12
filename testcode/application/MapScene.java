package application;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.Random;

import com.sun.corba.se.pept.transport.Acceptor;
import com.sun.corba.se.pept.transport.Connection;
import com.sun.corba.se.pept.transport.EventHandler;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class MapScene extends Scene implements EventHandler
{
	
	Player p;
	//Inherited Contructors
	public MapScene(Parent arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
		setEventHandler(KeyEvent.ANY, Key);
	}
	public MapScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		// TODO Auto-generated constructor stub
	}
	public MapScene(Parent root, double width, double height, boolean depthBuffer) {
		super(root, width, height, depthBuffer);
		// TODO Auto-generated constructor stub
	}
	public MapScene(Parent root, double width, double height, Paint fill) {
		super(root, width, height, fill);
		// TODO Auto-generated constructor stub
	}
	public MapScene(Parent root, double width, double height) 
	{
		super(root, width, height);
		// TODO Auto-generated constructor stub
	}
	public MapScene(Parent root, Paint fill) {
		super(root, fill);
		// TODO Auto-generated constructor stub
	}
	public void handleEvent(EventHandler e)
	{
		if(e.)
	}
	public void checkCollisions()
	{
		p = new Player("");
	}
	@Override
	public Acceptor getAcceptor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SelectableChannel getChannel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getInterestOps() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public SelectionKey getSelectionKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Work getWork() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSelectionKey(SelectionKey arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setUseSelectThreadToWait(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setUseWorkerThreadForEvent(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setWork(Work arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean shouldUseSelectThreadToWait() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean shouldUseWorkerThreadForEvent() {
		// TODO Auto-generated method stub
		return false;
	}

}
