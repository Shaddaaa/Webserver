package main;

import serverLogic.Webserver;

public class Main {
	public static void main(String[] args) {
		//starts a new Webserver to listen on 
		//TODO port from config file
		new Webserver();
	}
}
