import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class State{
	
	//The player currently playing.
	boolean hasChildren = false;
	private char player;
	private int[] currentPosA = {0,0};
	private int[] currentPosB = {0,0};
	private int[] oldPosA = {0,0};
	private int[] oldPosB = {0,0};
	private State parent;
	private State [] children ;
	//Who wins.
	private int win;
	private static int M ;
	private static int N ;
	private char [][] board;
	//"B" = Black and "W" = White
	public State(int M,int N,char[][] oldBoard){
		System.out.println("I am in the constructor");
		this.M = M ;
		this.N = N ;
		this.board = new char[M][N];
		for (int i = 0; i<M ; i++){
			for (int j = 0 ; j < N ; j++){
				this.board[i][j] = oldBoard[i][j];
			}
		}
		this.children = new State[8];
		/*for (int i = 0; i<8 ; i++){
			children[i].set_player(null);
		}*/
	}


	//Get & Set
	char[][] get_board(){
		return this.board ;
	}

	int get_win(){
		return this.win;
	}
	
	void set_win(int win){
		this.win = win;
	}
	
	char get_player(){
		return this.player;
	}
	
	void set_player(char player){
		this.player = player;
	}
	int [] get_pos_player(){
		int[] temp2 = new int[2];
		if (this.player == 'A'){
			for (int i = 0; i<M ; i++){
				for (int j = 0 ; j < N ; j++){
					if (board[i][j] == 'A'){
						this.currentPosA[0]= i ;
						this.currentPosA[1]= j ;
						temp2[0]=i;
						temp2[1]=j;
					}
				}	
			}
			
		}
		else{
			for (int i = 0; i<M ; i++){
				for (int j = 0 ; j < N ; j++){
					if (board[i][j] == 'X'){
						this.currentPosB[0]= i ;
						this.currentPosB[1]= j ;
						temp2[0]=i;
						temp2[1]=j;

					}
				}
			}
		}
		return temp2;
	}
	void set_pos_player(int[] pos){
		if (this.player == 'A'){
			this.currentPosA[0] = pos[0];
			this.currentPosA[1] = pos[1];
		}
		else{
			this.currentPosB[1] = pos[1];
			this.currentPosB[0] = pos[0];
		}
	}

	int [] get_oldpos_player(){
		if (this.player == 'A'){
			return oldPosA;
		}
		else{
			return oldPosB;
		}
	}
	void set_oldpos_player(int [] pos){
		if (this.player == 'A'){
			this.oldPosA[0] = pos[0];
			this.oldPosA[1] = pos[1];
		}
		else{
			this.oldPosB[0] = pos[0];
			this.oldPosB[1] = pos[1];	
		}
	}
	
	State get_parent(){
		return this.parent;
	}
	
	void set_parent(State parent){
		this.parent = parent;
	}
	
	void add_Child (State state){
		int i = 0;
		
		while (this.children[i] != null ){
			i++;
		}
		this.children[i] = state;
	}

	void clear_Children(){
		this.children = null;
		System.gc();
	}
	
	State[] get_children(){
		return this.children ;
	}

	
	void move(String mov, int num ){//Prepei na tsekaroume kai thn periptwsh an o enas paixths peftei panw ston allon.
		int temp[] = get_pos_player();
		int r = temp[0];
		int c = temp[1];
		if (mov == "up"){
			int temp1[] = {r-1,c};
			State child = new State(this.M,this.N,this.board);
			if( temp[0] != 0 && this.board[temp[0]-1][temp[1]] == 'W' ){
				hasChildren = true;
				child.set_pos_player(temp1);	
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}
		}
		else if (mov == "down"){
			int temp1[] = {r+1,c};
			State child = new State(this.M,this.N,this.board);
			if( temp[0] != M-1 && this.board[temp[0]+1][temp[1]] == 'W' ){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}
		}
		else if (mov == "left"){
			int temp1[] = {r,c-1};
			State child = new State(this.M,this.N,this.board);;
			if( temp[1] != 0  && this.board[temp[0]][temp[1]-1] == 'W' ){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[r][c] = 'B';//child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[r][c] = get_player();//child.get_board()[temp1[0]][temp1[1]]
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}
		}
		else if (mov == "right"){
			int temp1[] ={r,c+1};
			State child = new State(this.M,this.N,this.board);
			if( temp[1] != N-1  && this.board[temp[0]][temp[1]+1] == 'W' ){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}
		}
		else if (mov == "upleft"){
			int temp1[] = {r-1,c-1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != 0  && temp[0] != 0)  && this.board[temp[0]-1][temp[1]-1] == 'W' ){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}
		}
		else if (mov == "upright"){
			int temp1[]= {r-1,c+1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != N-1  && temp[0] != 0)  && this.board[temp[0]-1][temp[1]+1] == 'W' ){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}			
		}
		else if (mov == "downleft"){
			int temp1[]= {r+1,c-1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != 0  && temp[0] != M-1)  && this.board[temp[0]+1][temp[1]-1] == 'W' ){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}
		}
		else if (mov == "downright"){
			int temp1[]= {r+1,c+1};
			State child = new State(this.M,this.N,this.board);
			if( (temp[1] != N-1  && temp[0] != M-1)  && this.board[temp[0]+1][temp[1]+1] == 'W'){
				hasChildren = true;
				child.set_pos_player(temp1);
				child.set_oldpos_player(temp);
				child.get_board()[temp[0]][temp[1]] = 'B';
				child.get_board()[temp1[0]][temp1[1]] = get_player();
				if (get_player() == 'A') {				
					child.set_player('X');
				}else{
					child.set_player('A');
				}
				add_Child(child);
				
				for (int i = 0; i<M ; i++){
					for (int j = 0 ; j < N ; j++){
						System.out.print(child.board[i][j]);
					}
					System.out.println("");
				}
			}
		}
		//System.out.println(child.get_board());
	}
}


class Tree{
	String all_possible_moves[] = {"up","down","right","left","upright","upleft","downright","downleft"};
	int minimaxer (State state , char player){
		int score = 0;
		//int x = 0;
		//String all_possible_moves[] = {"up","down","right","left","upright","upleft","downright","downleft"};
		for (String i : this.all_possible_moves){
			System.out.println("The next move is :"+ i);
			state.move(i,1);
			System.out.println("");
		}//Check win..
		
		if (!(state.hasChildren)){
			if (player == 'A') {
				score = -1;
			}else{
				score = 1;
			}
			return score;
			//Check who wins.
			//Return 1 or -1.
		}
		
		if (state.get_player() == 'A'){
			score = -2;
			//Σε αυτό το μέρος του κώδικα έχουμε το εξής λάθος,έχουμε γεμίσει την children με null και κάθε φορά που δημιουργούμε ένα παιδί το βάζουμε εδώ στην θέση ενός null. Στην περίπτωση που τα παιδιά είναι κάτω από 8 , τότε ο κώδικας ανατρέχει σε όλα τα παιδιά (με την for, ακόμα και σε αυτά που είναι null) και κάνει τα operations ακόμα και με τις null τιμές γι'αυτό το λόγο έχουμε NullPointerException.
			/*for (State child : state.get_children()){
				if (child != null){
					System.out.println("Child");
				}
				
				int maxScore = minimaxer(child ,'X');
				score = Math.max(score , maxScore);		
			}*/
			for (State child : state.get_children()){
				if (child == null){
					System.out.println("No more Children");
					continue;
				}
				
				int maxScore = minimaxer(child ,'X');
				score = Math.max(score , maxScore);		
			}
		}else{
			score = 2;
			//Την ίδια περίπτωση με την παραπάνω συναντάμε και εδώ.
			/*for (State child : state.get_children()){
				if (child != null){
					System.out.println("Child");
				}
				
				int minScore = minimaxer(child ,	'A');
				score = Math.min(score , minScore);	
			}*/
			for (State child : state.get_children()){
				if (child == null){
					System.out.println("Not a children");
					continue;
				}
				
				int minScore = minimaxer(child ,	'A');
				score = Math.min(score , minScore);	
			}
		}
		return score;
			
		//Give me all the possible moves for player when in state.
	}
}


class minimax{
	public static void main(String[] args){
		//{{'A','W','W'},{'W','W','W'},{'W','W','X'}}
		char[][] board1 = new char[3][3];
		for (int i = 0; i<3 ; i++){
			for (int j = 0 ; j < 3 ; j++){
					board1[i][j]= 'W' ;
				}
		}		
		board1[0][0] = 'A';
		board1[2][2] = 'X';
		State example = new State(3,3,board1 ) ;
		Tree game = new Tree();
		//score = game.minimax(example , 'A');
		/*if (score == 1) {
			"AI won"
		}else{
			// 
		*/



		example.set_player('A');
		int score = game.minimaxer(example , 'A');
		System.out.println(score);
		

	}
}



	
	
