



class State{
	
	//The player currently playing.
	private String player;
	private int[] currentPosA = [0,0];
	private int[] currentPosB = [0,0];
	private int[] oldPosA = [0,0];
	private int[] oldPosB = [0,0];
	private State parent;
	private State [8] children;
	//Who wins.
	private int win;
	private char [][] board ;
	//"B" = Black and "W" = White
	void State(int M,int N,char[][] oldBoard){
		board = new char [M][N];
		for (int i = 0; i<M ; i++){
			for (int j = 0 ; j < N ; j++){
				board[i][j] = oldBoard[i][j];
			}
		}
		for (int i = 0; i<8 ; i++){
			children[i].set_player("null");
		}
	}


	//Get & Set
	int get_win(){
		return this.win;
	}
	
	void set_win(int win){
		this.win = win;
	}
	
	String get_player(){
		return this.player;
	}
	
	void set_player(String player){
		this.player = player;
	}

	int [] get_pos_player_A(){
		return currentPosA[];
	}
	
	void set_pos_player_A(int [] pos){
		this.currentPos[0] = pos[0];
		this.currentPosA[1] = pos[1];
	}

	int [] get_pos_player_B(){
		return currentPosB[];
	}
	
	void set_pos_player_B(int [] pos){
		this.currentPosB[1] = pos[1];
		this.currentPosB[0] = pos[0];
	}

	int [] get_oldpos_player_B(){
		return oldPosB[];
	}
	
	void set_oldpos_player_B(int [] pos){
		this.oldPosB[0] = pos[0];
		this.oldPosB[1] = pos[1];
	}
	
	int [] get_oldpos_player_A(){
		return oldPosA[];
	}
	
	void set_oldpos_player_A(int [] pos){
		this.oldPosA[0] = pos[0];
		this.oldPosA[1] = pos[1];
	}
	
	State get_parent(){
		return this.parent;
	}
	
	void set_parent(State parent){
		this.parent = parent;
	}
	
	void add_Child (State state){
		int i = 0;
		while (this.children[i].get_player() != "null"){
			i++;
		}
		this.children[i] = state;
	}

	void clear_Children(){
		this.children = null;
		System.gc();
	}

	
	
