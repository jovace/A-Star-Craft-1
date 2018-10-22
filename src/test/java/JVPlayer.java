import java.util.*;
import java.io.*;
import java.math.*;

enum DIR {U,D,L,R};

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char[][] world= new char[10][];
        for (int i = 0; i < 10; i++) {
            String line = in.next();
            char[] linea = line.toCharArray();
            world[i]=linea;
        }
        BattleGround bg = new BattleGround(world);
        List<Individual> robots=new ArrayList<Individual>();
        int robotCount = in.nextInt();
        for (int i = 0; i < robotCount; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            String direction = in.next();
            
            DIR dir;
            if(direction=="U") {
    			dir=DIR.U;
    		}else if(direction=="R") {
    			dir=DIR.R;
    		}else if(direction=="L") {
    			dir=DIR.L;
    		}else{
    			dir=DIR.D;
    		}
            
            Individual ind=new Individual(x,y,dir,bg,0,i);
            robots.add(ind);
        }
        
        bg.addInitialPopulation(robots);
        
        bg.nextFrame();
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("0 0 U 1 1 R 2 2 D 3 3 L");
    }
}

class BattleGround{
	char[][] platform;
	int maxX,maxY;
	List<Individual> population;
	int bestScore;
	
	public BattleGround(char[][] platform) {
		this.platform=platform;
		this.maxX=platform.length;
		this.maxY=platform[0].length;
	}
	
	public void addInitialPopulation(List<Individual> robots) {
		this.population=robots;
	}
	
	public void nextFrame() {
		List<Individual> newIndiv = new ArrayList<Individual>();
		for(Individual ind : population) {
			int[] next=ind.nextDecision();
			int x=next[0];
			int y=next[1];
			if(cellStatus(x+1,y)==-2) {
				Individual desc = new Individual(x+1,y,DIR.L,this,ind.getScore(),ind.getType());
				newIndiv.add(desc);
			}
			if(cellStatus(x-1,y)==-2) {
				Individual desc = new Individual(x-1,y,DIR.R,this,ind.getScore(),ind.getType());
				newIndiv.add(desc);
			}
			if(cellStatus(x,y+1)==-2) {
				Individual desc = new Individual(x,y+1,DIR.D,this,ind.getScore(),ind.getType());
				newIndiv.add(desc);
			}
			if(cellStatus(x,y-1)==-2) {
				Individual desc = new Individual(x,y-1,DIR.U,this,ind.getScore(),ind.getType());
				newIndiv.add(desc);
			}
		}
		
		this.population.addAll(newIndiv);
		
		for(Individual ind : population) {
			ind.doDecision();
		}
		
		
		//Seleccion
		selection();
		
		
		
		
	}
	
	private void selection() {
		this.population.sort((a,b)-> Integer.compare(a.bestScore, b.bestScore));
		
		System.err.println(this.population);
	}

	public int cellStatus(int x, int y) {
		if(x<0 || x>maxX || y<0 || y>maxY || platform[x][y]=='.') {
			return -1;
		}else if(platform[x][y]=='U') {
			return 1;
		}else if(platform[x][y]=='R') {
			return 0;
		}else if(platform[x][y]=='L') {
			return 2;
		}else if(platform[x][y]=='D') {
			return 3;
		}else {
			return -2;
		}
	}
	
}

class Individual{
	int type;
	List<Gene> genoma;
	int bestScore;
	int posX;
	int posY;
	DIR dir;
	BattleGround world;
	
	int nextX;
	int nextY;
	
	public Individual(int posX, int posY, DIR dir, BattleGround world, int score, int type) {
		this.posX=posX;
		this.posY=posY;
		this.dir=dir;
		this.world=world;
		this.bestScore = score;
		this.type=type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void doDecision(){
		int[] next=nextStep();
		int nextX=next[0];
		int nextY=next[1];
		
		while(world.cellStatus(nextX, nextY)==-2){
			this.posX=nextX;
			this.posY=nextY;
			next=nextStep();
			nextX=next[0];
			nextY=next[1];
			this.bestScore++;
		}
		
		this.nextX=nextX;
		this.nextY=nextY;
	}
	
	public int[] nextDecision() {
		return new int[] {this.nextX,this.nextY};
	}
	
	public int[] nextStep() {
		if(dir==DIR.R) {
			return new int[]{this.posX+1,this.posY};
		}else if(dir==DIR.U) {
			return new int[] {this.posX,this.posY+1};
		}else if(dir==DIR.L) {
			return new int[] {this.posX-1,this.posY};
		}else{
			return new int[] {this.posX,this.posY-1};
		}
	}
	
	public int getScore() {
		return this.bestScore;
	}
}

class Gene{
	public int x;
	public int y;
	public DIR dir;
}