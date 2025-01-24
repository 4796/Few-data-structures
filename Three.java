import java.util.LinkedList;
import java.util.List;

//import Queue.RedStaticki;

public class Stablo {

	CvorStabla koren;
	public Stablo() {
		koren=null;
	}
	public void infiks(CvorStabla k) throws Exception {
		if(koren==null)
			throw new Exception("Stablo ne postoji");
		if(k==null)
			return;
		infiks(k.levi);
		System.out.println(k.podatak);
		infiks(k.desni);
	}
	
/*	public void infiksSekvencijalno() throws Exception  {
		if(koren==null)
			throw new Exception("Stablo ne postoji");
		List<Integer> l=new LinkedList<>(); 
		l.add(koren.podatak);
		while(true) {
			
		}
	}
	*/
	public void sufiks(CvorStabla k) throws Exception {
		if(koren==null)
			throw new Exception("Stablo je prazno");
		if(k==null)
			return;
		
		sufiks(k.levi);
		sufiks(k.desni);
		System.out.println(k.podatak);
		
	}

	public void prefiks(CvorStabla k) throws Exception {
	if(koren==null)
		throw new Exception("Stablo ne postoji");
	if(k==null)
		return;
	
	System.out.println(k.podatak);
	prefiks(k.levi);
	prefiks(k.desni);
	
	}
	/*
	public void poSirini(CvorStabla k) {
		if(k==null)
			return;
	
		
		RedStaticki red=new RedStaticki();
		try {
			red.ubaci(k.podatak);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(k==koren) {
			red.procitajSve();
		}
	}
	*/
	public int zbir(CvorStabla k) throws Exception {
		if(koren==null)
			throw new Exception("Stablo je prazno");
		if(k==null)
			return 0;
		int suma=k.podatak;
		return suma+zbir(k.levi)+zbir(k.desni);
	}
	
	public boolean isEmpty() {
		return koren==null;
	}
	
	private int broj(CvorStabla k) {
		if(k==null)
			return 0;
		
		return 1+broj(k.levi)+broj(k.desni);
	}
	
	public int brojELemenata() throws Exception {
		if(koren==null) 
			throw new Exception("Stablo je prazno");

		int n=1;
		return n+broj(koren.levi)+broj(koren.desni);
	}
	
	public double prosek(CvorStabla k) throws Exception {
		
			return (double)zbir(k)/brojELemenata();
		
	}
	
	private class KlasaZaProsek {
		int broj=0;
		int zbir=0;
	}
	
	private double prosekPrivate(CvorStabla k, KlasaZaProsek pro) {
		if(k==null)
			return 0;
		pro.broj++;
		pro.zbir=k.podatak+pro.zbir;
		prosekPrivate(k.levi, pro);
		prosekPrivate(k.desni, pro);
		return pro.zbir/pro.broj;
	}
	
	public double prosekJedanProlaz(CvorStabla k) throws Exception {
		if(k==null)
			throw new Exception("Stablo je prazno");
		
		
		return prosekPrivate(k, new KlasaZaProsek());
	}
	
	
	private int[] prosekSaNizomRekurzivna(CvorStabla k, int[] zbirIBroj) {
		if(k==null)
			return zbirIBroj;
		zbirIBroj[0]=zbirIBroj[0]+1;
		zbirIBroj[1]=zbirIBroj[1]+k.podatak;
		zbirIBroj=prosekSaNizomRekurzivna(k.levi, zbirIBroj);
		zbirIBroj=prosekSaNizomRekurzivna(k.desni, zbirIBroj);
		
		return zbirIBroj;
	}
	
	public double prosekSaNizom() throws Exception {
		if(this.koren==null)
			throw new Exception("Stablo je prazno");
		int[] niz= {0, 0};
		int[] zbirIBroj=prosekSaNizomRekurzivna(koren, niz);
		
		
		return (double)zbirIBroj[1]/zbirIBroj[0];
	}
	
	private CvorStabla maksCvor(CvorStabla k, CvorStabla max) {
		if(k==null)
			return max;
	
		if(k.podatak>max.podatak) {
			max=k;
		
		}
			
		max=maksCvor(k.levi, max);
		max=maksCvor(k.desni, max);
		return max;
	}
	
	public CvorStabla maksimalniCvor(CvorStabla k) throws Exception {
		if(this.koren==null)
			throw new Exception("Stablo ne postoji");
		
		return maksCvor(k, k);
	}
	
	private class Maxi{
		int max=0;
		
	}
	
	private int maksimalnaVrednostRekurzivna(CvorStabla k, Maxi max) {
		if(k==null)
			return max.max;
		if(k.podatak>max.max)
			max.max=k.podatak;
		maksimalnaVrednostRekurzivna(k.levi, max);
		maksimalnaVrednostRekurzivna(k.desni, max);
		return max.max;
	}
	
	public int maksimalnaVrednost() throws Exception {
		if(this.koren==null)
			throw new Exception("Nema stabla");
		Maxi max= new Maxi();
		max.max=koren.podatak;
		return maksimalnaVrednostRekurzivna(koren, max);
	}

	private int visinaRekurzivna(CvorStabla k, int n) {
		if(k==null)
			return n;
		n++;
		int levi=visinaRekurzivna(k.desni, n);
		int desni=visinaRekurzivna(k.levi, n);
		if(levi>n)
			n=levi;
		if(desni>n)
			n=desni;
		return n;
	}
	
	
	public int visina() throws Exception {
		if(this.koren==null)
			throw new Exception("Stablo je prazno");
		return visinaRekurzivna(koren, 0);
		
	}
	
	public CvorStabla vratiRoditeljaBroja(int broj, CvorStabla k) throws Exception {
		if(koren==null)
			throw new Exception("Stablo je prazno");
		if(koren.podatak==broj)
			return null;
		if(k==null)
			return null;
		//System.out.println("a "+k.podatak);
		if((k.levi!=null && k.levi.podatak==broj) || (k.desni!=null && k.desni.podatak==broj))
			return k;
		CvorStabla levi = vratiRoditeljaBroja(broj, k.levi);
		if(levi!=null)
			return levi;
		CvorStabla desni = vratiRoditeljaBroja(broj, k.desni);
		if(desni!=null)
			return desni;
		if(k==koren)
		throw new Exception("Nema datog broja");
		return null;
	}
	
	private void ubaciRekurzivno(CvorStabla k, int p) {
		if(k==null)
			return;
		if(p<k.podatak) {
			if(k.levi==null)
				k.levi=new CvorStabla(p, null, null);
			else
				ubaciRekurzivno(k.levi, p);
		}else {
			if(k.desni==null)
				k.desni=new CvorStabla(p, null, null);
			else
				ubaciRekurzivno(k.desni, p);
		} 
	}
	
	public void ubaci(int p) {
		if(this.koren==null) {
			this.koren=new CvorStabla(p, null, null);
			return;
		}
		ubaciRekurzivno(koren, p);
	}
	
	private CvorStabla minimumRoditelj(CvorStabla k) throws Exception {
		//AI ???
		if(koren == null)
	        throw new Exception("Stablo je prazno");
	    
	    CvorStabla trenutni = k;
	    CvorStabla roditelj = null;
	    CvorStabla roditeljNajmanjeg = null;
	    int minVrednost = Integer.MAX_VALUE;

	    List<CvorStabla> queue = new LinkedList<>();
	    queue.add(trenutni);

	    while (!queue.isEmpty()) {
	        trenutni = ((LinkedList<CvorStabla>) queue).poll();

	        if (trenutni.podatak < minVrednost) {
	            minVrednost = trenutni.podatak;
	            roditeljNajmanjeg = roditelj;
	        }

	        roditelj = trenutni;

	        if (trenutni.levi != null) {
	            queue.add(trenutni.levi);
	        }

	        if (trenutni.desni != null) {
	            queue.add(trenutni.desni);
	        }
	    }

	    return roditeljNajmanjeg;
	}
	
	private boolean izbaciRekurzivno(CvorStabla k, CvorStabla roditelj, int p) throws Exception {
		if(k==null)
			return false;
		if(k.podatak==p) {
			if(k.levi==null && k.desni==null) {
				//list
				if(roditelj!=null) {
					if(k==roditelj.levi) 
						roditelj.levi=null;
					else 
					roditelj.desni=null;
				}
				else
					this.koren=null;
			}
			else if(k.levi==null) {
				//polu list
				if(k==roditelj.levi)
					roditelj.levi=k.desni;
				else
					roditelj.desni=k.desni;
			}else if(k.desni==null) {
				//polu list
				if(k==roditelj.levi)
					roditelj.levi=k.levi;
				else
					roditelj.desni=k.levi;
			}else {
				//nije list

				CvorStabla novi=minimumRoditelj(k.desni);
				if(novi.desni!=null && novi.levi!=null) {
					if(novi.levi.podatak<novi.desni.podatak) {
						k.podatak=novi.levi.podatak;
						izbaciRekurzivno(novi.levi, novi, novi.levi.podatak);
					}
					else {
						k.podatak=novi.desni.podatak;
						izbaciRekurzivno(novi.desni, novi, novi.desni.podatak);
					}
				}else if(novi.levi==null) {
					k.podatak=novi.desni.podatak;
					izbaciRekurzivno(novi.desni, novi, novi.desni.podatak);
				}else if(novi.desni==null) {
					k.podatak=novi.levi.podatak;
					izbaciRekurzivno(novi.levi, novi, novi.levi.podatak);
				}
			}

			return true;
		}
		else {
			//idem  na sledeci cvor
			Boolean l1=false;
			Boolean d1=false;
			if(k.levi!=null) {
				 l1=izbaciRekurzivno(k.levi, k, p);
			}
			if(l1)
				return true;
			if(k.desni!=null) {
				 d1=izbaciRekurzivno(k.desni, k, p);
			}
			if(d1)
				return true;
		}
		//nenadjeno
		return false;
	}
	
	public void izbaci(int p) throws Exception {
		if(koren==null)
			throw new Exception("Stablo je prazno");
		if(!izbaciRekurzivno(koren, null, p))
			throw new Exception("Nema datog podatka");
	}
}
