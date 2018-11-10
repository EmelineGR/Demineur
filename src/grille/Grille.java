package grille;
//import java.util.List;

public class Grille 
{
	private Case[][] grille;
	private int taille;
	
	public void setTaille(int taille)
	{
		this.taille = taille;
	}
	public int getTaille()
	{
		return this.taille ;
	}
	
	public Grille(int taille)
	{
		setTaille(taille);
		grille = new Case[taille][taille];
	}
	public void remplirGrille()
	{
		for (int j = 0; j < this.taille; j++)
		{
			for (int i = 0; i < this.taille; i++)
			{
				grille[i][j] = new Case(0);
				grille[i][j].setDecouverte(false);
			}
		}			
	}
	public void remplirMine()
	{
		//TO DO TRES LONG
		for (int j = 0; j < this.taille; j++)
		{
			for (int i = 0; i < this.taille; i++)
			{
				int n = (int)(Math.random() * 6);
				if(n==5)
				{
					grille[i][j].setMine(true);
					addValue(i-1,j-1);
					addValue(i,j-1);
					addValue(i-1,j);
					addValue(i+1,j-1);
					addValue(i-1,j+1);
					addValue(i+1,j);
					addValue(i,j+1);
					addValue(i+1,j+1);
				}
			}
		}				
	}
	public void addValue(int x,int y)
	{
		if( x >=0 && y >=0 && x < taille && y< taille)
		{
			grille[x][y].setValeur(grille[x][y].getValeur()+1);
		}
	}
	public void afficherGrille()
	{
		for (int k = 0; k < this.taille; k++)
		{
			System.out.print("|");
			System.out.print(k);
		}
		System.out.print("|");
		System.out.println();
		for (int l = 0; l < this.taille; l++)
		{
			System.out.print("--");
		}
		System.out.println();
		for (int j = 0; j < this.taille; j++)
		{
			System.out.print("|");
			for (int i = 0; i < this.taille; i++)
			{
				if(grille[i][j].getDecouverte())
				{
					if(grille[i][j].getMine())
					{
						System.out.print("*");
					}
					else {System.out.print(grille[i][j].getValeur());}
				}
				else 
				{ 
					if(grille[i][j].getDrapeau())
					{
						System.out.print("p");
					}
					else {System.out.print("?");}
					
				}
				
				System.out.print("|");
			}
			System.out.print(" " + j);
			System.out.println();
		}		
	}
	public void devoilerCase(int x, int y)
	{
		if( x >=0 && y >=0 && x < taille && y< taille) 
		{ 
			grille[x][y].setDecouverte(true);
			if (grille[x][y].getValeur() == 0)
			{
				procheZero(x-1,y-1);
				procheZero(x-1,y);
				procheZero(x,y-1);
				procheZero(x-1,y+1);
				procheZero(x+1,y-1);
				procheZero(x,y+1);
				procheZero(x+1,y);
				procheZero(x+1,y+1);
			}
		}
		
	}
	public void procheZero(int x, int y)
	{
		if( x >=0 && y >=0 && x < taille && y< taille)
		{
			if (!grille[x][y].getDecouverte())
			{
				grille[x][y].setDecouverte(true);
				if (grille[x][y].getValeur() == 0)
				{
					procheZero(x-1,y-1);
					procheZero(x-1,y);
					procheZero(x,y-1);
					procheZero(x-1,y+1);
					procheZero(x+1,y-1);
					procheZero(x,y+1);
					procheZero(x+1,y);
					procheZero(x+1,y+1);
				}
			}
		}
	}
	public void mettreDrapeau(int x, int y)
	{
		if( x >=0 && y >=0 && x < taille && y< taille)
		{
			if (grille[x][y].getDrapeau())
			{
				grille[x][y].setDrapeau(false);
			}
			else {grille[x][y].setDrapeau(true);}
		}
	}
	public boolean presenceMine(int x, int y)
	{
		return grille[x][y].getMine();
	}
	public boolean caseDev(int x, int y)
	{
		return grille[x][y].getDrapeau();
	}
	public boolean verifierWin()
	{
		for (int j = 0; j < this.taille; j++)
		{
			for (int i = 0; i < this.taille; i++)
			{
				if(!caseDev(j,i) && !presenceMine(j,i))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	
	
}
