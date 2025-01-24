package main;


import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.stabla.ABSTStablo;

public class BSTStablo extends ABSTStablo {

	public void ispis(CvorStabla k) {
		if(k==null)
			return;
		ispis(k.levo);
		System.out.println(k.podatak);

		ispis(k.desno);

	}
	private void ubacirek(int podatak, CvorStabla k) throws LabisException {
		
		if(podatak==k.podatak)
			throw new LabisException("Dati podatak je vec unet");
		if(podatak<k.podatak) {
			if(k.levo==null) {
				k.levo=new CvorStabla(podatak);
				return;
		}
			ubacirek(podatak, k.levo);
			return;
	}

		if(podatak>k.podatak) {
			if(k.desno==null) {
				k.desno=new CvorStabla(podatak);
				return;
		}
			ubacirek(podatak, k.desno);
			return;
	}
	}

	public void ubaci(int podatak) throws LabisException {
		if(koren==null) {
		this.koren=new CvorStabla(podatak);
		this.koren.desno=null;
		this.koren.levo=null;
		return;
	}
		if(podatak==koren.podatak)
			throw new LabisException("Dati podatak je vec unet");
		ubacirek(podatak, koren);
	}	
	
	private boolean sadrzi(int p, CvorStabla k) {
		if(k==null)
			return false;
		if (k.podatak==p)
				return true;
		return sadrzi(p, k.levo) || sadrzi(p, k.desno);

	}
	
	private CvorStabla najveciCvor(CvorStabla k) {
		CvorStabla keva=null;
		while(k.desno!=null) {
			if(k.desno.desno==null) {
				keva=k;
				k=k.desno;
				break;
			}
				
			k=k.desno;
		}


		
		return k;
	}
	
	private void izbacirek(CvorStabla k, CvorStabla keva, int p) throws LabisException {
		CvorStabla max=null;
		
		
		if(k.podatak==p) {
			//naso
		
			//list
		if(k.desno==null && k.levo==null) {
			
			if(keva.podatak>p) 
				keva.levo=null;	
			else
				keva.desno=null;
			return;
			}
		
		//nije list
		if(k.desno!=null && k.levo!=null) {
			max=najveciCvor(k.levo);
			izbaci(max.podatak);
			max.levo=k.levo;
			max.desno=k.desno;
			if(keva.podatak>p) {
				keva.levo=max;
			}
			else
				keva.desno=max;
			return;
		}
		
		//polu list
		if(keva.podatak>p) {
			if(k.desno!=null)
				keva.levo=k.desno;
			else
				keva.levo=k.levo;
		}else {
			if(k.desno!=null)
				keva.desno=k.desno;
			else
				keva.desno=k.levo;
		}
		
		return;
		}
		
		if(p>k.podatak) {
			izbacirek(k.desno, k, p);
		}else
			izbacirek(k.levo, k, p);
		//rekurzija
	}
	
	@Override
	public void izbaci(int podatak) throws LabisException {
		if(koren==null || !sadrzi(podatak, koren))
			throw new LabisException("Nema tog podatka");
		if(koren.podatak==podatak) {
			if(koren.levo==null && koren.desno==null) {
				this.koren=null;
				return;
			}
			CvorStabla max=null;
			if(koren.levo!= null && koren.desno!=null) {
				max=najveciCvor(koren.levo);
				izbaci(max.podatak);
				max.levo=koren.levo;
				max.desno=koren.desno;
				koren=max;
				return;
			}
			
			
			if(koren.desno!=null)
				koren=koren.desno;
			else
				koren=koren.levo;
				
			
			
			return;
		}
		izbacirek(koren, null, podatak);
	}
	
	private CvorStabla makspolu(CvorStabla tren, CvorStabla maks) {
		if (tren==null) return maks;
		if(!(tren.levo== null && tren.desno==null) && !(tren.desno!=null && tren.levo!=null)) {
			if(maks==null)
				maks=tren;
			else {
				if(tren.podatak>maks.podatak)
					maks=tren;
			}
		}
		

		CvorStabla maxl=makspolu(tren.levo, maks);
		CvorStabla maxd=makspolu(tren.desno, maks);
		if(maxl!=null) {
			if(maks==null)
				maks=maxl;
			else
				if(maxl.podatak>maks.podatak)
					maks=maxl;
		}
		if(maxd!=null) {
			if(maks==null)
				maks=maxd;
			else
				if(maxd.podatak>maks.podatak)
					maks=maxd;
		}	
		return maks;
	}
	
	@Override
	public CvorStabla vratiMaksimalanPolulist(CvorStabla k) throws LabisException {
		if(k==null)
			throw new LabisException("Stablo je prazno");
		CvorStabla cv=makspolu(k, null);
		if (cv==null)
			throw new LabisException("Nema polulistova");
		return cv;
	}
	
	private int visina(CvorStabla k) {
		if(k==null)
			return 0;
		return 1+ Math.max(visina(k.levo), visina(k.desno));

	}
	
	private boolean avl(CvorStabla k) {
		if(k==null)
			return true;
		int l=visina(k.levo);
		int d=visina(k.desno);
		if(Math.abs(d-l)>1) {
			
			return false;
		}
		

		
		return avl(k.levo) && avl(k.desno);
	}
	@Override
	public boolean daLiJeAVL(CvorStabla k) throws LabisException {
		if(k==null)
			throw new LabisException("Stablo je prazno");
		return avl(k);
	}
	
}
