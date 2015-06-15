package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import module.Player;
import module.Team;
import view.Input;

public class Baseball {

	public static void main(String[] args)  {
		MainMenu mainMenu = new MainMenu();
		mainMenu.runMainProgram();
	}
}
