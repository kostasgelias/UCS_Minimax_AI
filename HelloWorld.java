import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class State{

	private int g;
	private int h = 0;
	//label for sorted
	private boolean label;
	//name
	private int id;
	private ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>(); //The path to the sorted.
	//list
	private ArrayList<Integer> stateList;
	private Integer parent;
	private ArrayList<Integer> children=new ArrayList<Integer>();
	
	public State(int g,int h){
		this.g = g;
		this.h = h;
	}
	public State(int g){
		this.g = g;
	}
		
	//Setter for this label(If the state is sorted)
	public void isSorted(){
    	this.label = true;        
    	for (int i = 1; i < this.stateList.size(); i++) {
        	if (this.stateList.get(i-1).compareTo(this.stateList.get(i)) > 0){
				this.label = false;
			}
    	}
	}	



/*Getters 
	&
		Setters
*/
	//set , get gia parent
	public void ParentSet(int parent){
		this.parent = parent;
	}

	public Integer ParentGet(){
		return this.parent;
	}

	//set,get gia g kai h
	public void GSet(int g){
		this.g = g;
	}

	public int GGet(){
		return this.g;
	}
	
	public void HSet(){
		
		//This is the function H(n) to be applied in A*.
		for (int i = 0; i < this.stateList.size(); i++){
			if (this.stateList.get(i) != i+1){
				this.h = this.h + 1;
			}
		}
	}
	
	public int HGet(){
		return this.h;
	}

	//get , set gia stateList.
	
	public void set(ArrayList stateList){
		this.stateList = stateList;
	}
	
	public ArrayList get(){
		return this.stateList;
	}

	//Prosthiki paidiou sti lista
	public void addChild(int child){
		(this.children).add(Integer.valueOf(child));
	}

	//Afairesh paidiou apo th lista
	public void removeChild(int child){
		this.children.remove(child);
	}
	
	public ArrayList getChildren(){
		return this.children;
	}

	public void clearChildren(){
		this.children.clear();
	}
	
	public void id_Set(int id){
		this.id = id;
	}

	public int id_Get(){
		return this.id;
	}

	public ArrayList<ArrayList<Integer>> path_Get(){
		return this.path;
	}
	
	
	public void label_Set(boolean label){
		this.label = label;
	}

	public boolean label_Get(){
		return this.label;
	}

	//Prosthiki path sti lista
	public void add_path(ArrayList<ArrayList<Integer>> rootParent){
		this.path = ((ArrayList<ArrayList<Integer>>)rootParent.clone());
	}
	
	public void add_me(){
		(this.path).add(this.stateList);
	}
}




class StateTree{
	public void AStar(State root,int N ){
		//Current state to be checked.
		State state = root;
		//Filling the blanks in searchField.
		State defaultstate = new State(0,10000) ;
		System.out.println("The defaultState has g = " + defaultstate.GGet() + " and h = " +defaultstate.HGet());
		defaultstate.id_Set(-1) ;
		//If the list is seen before.
		boolean equality=false;
		//For the swap.
		int temp = 0;
		//For the ids.
		int id_Counter = 1;	
		//Lista me tis listes pou prospelasei
		ArrayList<State> searchField=new ArrayList<State>();
		//List with all the states that have been processed.
		ArrayList<State> All_States=new ArrayList<State>();
		searchField.add(root);
		int searchField_size = searchField.size();
		System.out.println("This is the searchField size: " + searchField_size);
		//int h = 0;
		while ( searchField_size > 0 ){
			//Checking the g.
			for (int d = 0 ; d < searchField_size ; d++){
				if (searchField.get(d).id_Get() == -1){
					continue;
				}else{
					if ((searchField.get(d).GGet()+searchField.get(d).HGet()) < (state.GGet() + state.HGet())){
						state = searchField.get(d);
					}
				}
			}
						
					
			for (int w = 0; w < All_States.size(); w++){
				if ((state.get()).equals(All_States.get(w).get())){
					equality=true;
					break;
				}else{
					equality=false;
				}
			}
			//Exec the commands if the list hasn't been produced again.
			if (equality==false){
				state.isSorted();
				if (state.label_Get()){
					if(state.id_Get() == 0){
						System.out.println("Root is the best solution");
						return;//We want the program to stop.
					}else{
						System.out.println("Printing the path to the solution...");
						System.out.println(state.path_Get()) ;
						System.out.println("The cost of the path and the steps to the path are the same : " + state.path_Get().size());
						return;
					}
				}else{
					All_States.add(state);
					//Produce the children.
					for (int k = 1 ; k < N ; k++){
						//Create the new child.
						State childState = new State(state.GGet()+1);
						//Assign an id to it.
						childState.id_Set(id_Counter);
						//Increment the id_Counter to give the id to the next one.
						id_Counter = id_Counter + 1;
						//copy list
						childState.set((ArrayList)(state.get().clone()));	
						//change with (T(K))
						for (int j = 0 ; j <= Math.floor(k/2); j++){
							Collections.swap(childState.get(),j,k-j);
						}
						//Add the child to the search field.
						searchField.add(childState);
						//Set the parent of the child.
						childState.ParentSet(state.id_Get());
						childState.HSet();
						//Add the child to the parent's list of childs.
						state.addChild(childState.id_Get());
						//searchField_size = searchField.size();
						childState.add_path(state.path_Get());
						childState.add_me();
					}
				}
			}

			searchField_size = searchField.size();
			searchField.set(state.id_Get(),defaultstate);
			if (state.id_Get()+1 >= searchField_size){
				state = searchField.get(state.id_Get()-1);
			}else{
				state = searchField.get(state.id_Get()+1);
			}
		}
	}
}


class HelloWorld{
	
	public static void main(String[] args){
		System.out.println("give me the size of the root_list");
		Scanner n=new Scanner(System.in);
		int N=n.nextInt();
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i =0; i<N; i++){
			System.out.println("give me the next number");
			int x=n.nextInt();
			while(list.contains(x) || (x<1 || x>N)){
				System.out.println("error.give me the next number");
				x=n.nextInt();
			}
			list.add(x);
		}
		State root=new State(0);
		root.set(list);
		root.id_Set(0);
		root.add_me();
		root.HSet();
	
		StateTree dentro=new StateTree();
		System.out.println("Now calculating the solution given by the UCS...");
		//dentro.UCS(root,N);
		System.out.println("Now calculating the solution given by the AStar...");
		dentro.AStar(root,N);

	}
}


