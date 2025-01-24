package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.generator.StabloGenerator;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo {

	
	private boolean sadrzi(CvorStabla tren, CvorStabla cvor) {
		if(tren==null)
			return false;
		if(tren.podatak==cvor.podatak)
			return true;

		
		
		return sadrzi(tren.levo, cvor) || sadrzi(tren.desno, cvor);
	}
	@Override
	public boolean daLiPostojiIsti(CvorStabla k, CvorStabla cvor) throws LabisException {
		if(k== null)
			throw new LabisException("Stablo je prazno");
		if(cvor==null)
			throw new LabisException("Nije prosledjen trazeni cvor");
		return sadrzi(k, cvor);
	}
	
	public int visina(CvorStabla k) {
		if (k==null)
			return 0;
		return 1+Math.max(visina(k.levo), visina(k.desno));

	}
	private CvorStabla najdublji(CvorStabla k, int v, int nivo) {
		if(k==null)
			return null;
		//System.out.println("v je "+v+" k je"+ visina(k));
		nivo++;
		if(nivo==v) {
			return k;
		}
		
		CvorStabla l=najdublji(k.levo, v, nivo);
		if (l!=null)
			return l;
		CvorStabla d=najdublji(k.desno, v, nivo);
		if (d!=null)
			return d;
		return null;

	}
	@Override
	public CvorStabla vratiCvorNaNajvecojDubini(CvorStabla k) throws LabisException {
		if(k==null)
			throw new LabisException("Stablo je prazno");
		if(k.levo==null && k.desno==null)
			return k;
		return najdublji(k, visina(k), 0);
	}
	
	private class CvorINivo{
		CvorStabla k=null;
		int nivo=0;
		public CvorINivo(CvorStabla cvor, int n) {
			this.nivo=n;
			this.k=cvor;
		}
	}
	
	private CvorINivo najplici(CvorINivo tren, CvorINivo naj) {
		if(tren.k==null)
			return naj;
		tren.nivo=tren.nivo+1;
		if(tren.k.levo==null && tren.k.desno==null) {
			if(tren.nivo<naj.nivo) {
				naj.nivo=tren.nivo;
				naj.k=tren.k;
			}
		}

		CvorINivo l = najplici(new CvorINivo(tren.k.levo, tren.nivo), naj);
		if(l.nivo<naj.nivo) {
			naj.k=l.k;
			naj.nivo=l.nivo;
		}
		CvorINivo d = najplici(new CvorINivo(tren.k.desno, tren.nivo), naj);
		if(d.nivo<naj.nivo) {
			naj.k=d.k;
			naj.nivo=d.nivo;
		}
		return naj;
	}
	
	@Override
	public CvorStabla vratiListNaNajmanjojDubini(CvorStabla k) throws LabisException {
		if(k==null)
			throw new LabisException("Stablo je prazno");
		return najplici(new CvorINivo(k, 0), new CvorINivo(k, Integer.MAX_VALUE)).k;
		
	}
	
	public boolean sadrzi1(CvorStabla k, CvorStabla t) {
		if(k==null)
			return false;
		if(k==t)
			return true;
		return sadrzi1(k.levo, t) || sadrzi1(k.desno, t);

	}
	private CvorStabla najNaPutanji(CvorStabla tren, CvorStabla konacni, CvorStabla max) {
		if (tren ==null)
			return max;
		if(tren.podatak>max.podatak) 
			max=tren;
		if(this.sadrzi1(tren.levo, konacni))
			max=najNaPutanji(tren.levo, konacni, max);
		else
			max=najNaPutanji(tren.desno, konacni, max);
		return max;
	}
	@Override
	public CvorStabla vratiNajveciNaPutanji(CvorStabla k, CvorStabla neki) throws LabisException {
		if(k==null)
			throw new LabisException("Stablo je prazno");
		if(neki==null)
			throw new LabisException("Nije prosledjen cvor");
		
		if(!this.sadrzi1(k, neki))
			throw new LabisException("Putanja nije validna");
		if(k==neki)
			return k;
		return najNaPutanji(k, neki, k);
	}
	
	private int max(CvorStabla k, int m) {
		if(k==null)
			return m;
		if(k.podatak>m)
			m=k.podatak;
		return Math.max(Math.max(m, max(k.levo, m)), max(k.desno, m));
		

	}
	
	private int brojVecih(CvorStabla k) {
		if(k==null)
			return 0;
		if(k.podatak>max(k.desno, Integer.MIN_VALUE) && k.podatak>max(k.levo, Integer.MIN_VALUE))
			return 1+brojVecih(k.levo)+brojVecih(k.desno);
			
		return brojVecih(k.levo)+brojVecih(k.desno);
	}
	
	@Override
	public int vratiBrojCvorovaVecihOdSvojihSledbenika(CvorStabla k) throws LabisException {
		if(k==null)
			throw new LabisException("Stablo je prazno");
		return brojVecih(k);
	}
	
	private int brojCvRek(CvorStabla k, int v) {
		if(k==null)
			return 0;
		if(brojJavljanja(k.desno, v)+brojJavljanja(k.levo, v)==1) {
			System.out.println("a"+k.podatak);
			return 1+brojCvRek(k.levo, v)+brojCvRek(k.desno, v);
		}
			
		else
			return brojJavljanja(k.levo, v)+brojJavljanja(k.desno, v);

	}
	
	public int brojJavljanja(CvorStabla k, int v) {
		if(k==null)
			return 0;
		if(k.podatak==v)
			return 1+brojJavljanja(k.levo, v)+brojJavljanja(k.desno, v);
		else
			return brojJavljanja(k.levo, v)+brojJavljanja(k.desno, v);

	}
	
	public int brojCvorovaCijiTacnoJedanPotomakImaVrednost(CvorStabla k, int v) throws LabisException{
		if(k==null)
			throw new LabisException("Stablo je prazno");
		return brojCvRek(k, v);
	}
	
	private class Min{
		CvorStabla rodMin;
		public Min(CvorStabla k) {
			rodMin=k;
		}
	}
	
	private void minRod(CvorStabla k, CvorStabla rodK,  CvorStabla min, Min rodMin) {
		if(k==null)
			return;
		if(k.podatak<min.podatak) {
			min=k;
			rodMin.rodMin=rodK;
		}
		minRod(k.levo, k, min, rodMin);
		minRod(k.desno, k, min, rodMin);
	}
	
	public CvorStabla roditeljNajmanjeg(CvorStabla k) throws LabisException {
		if (k==null)
			throw new LabisException("Stablo je prazno");
		if(k.desno== null && k.levo==null)
			throw new LabisException("Najmanji nema roditelja");
		
		Min rod=new Min(null);
		CvorStabla r1=null;
		minRod(k, r1, k, rod);

		return rod.rodMin;
	}

	

	private void zbirovi(CvorStabla tek, ArrayList<Integer> sume, int zbir) {

		if(tek==null)
			return;
		zbir=zbir+tek.podatak;
		if(tek.desno==null && tek.levo==null)
			sume.add(new Integer(zbir));
		zbirovi(tek.levo, sume, zbir);

		zbirovi(tek.desno, sume, zbir);
		
		return;
	}
	
	public int najveciZbirNaPutanjiOdKorenaDoLista(CvorStabla k) throws LabisException{
		if(k==null)
			throw new LabisException("Stablo je prazno");
		if(k.levo==null && k.desno==null)
			return k.podatak;
		
		ArrayList<Integer> zbirovi=new ArrayList<>();
		zbirovi(k, zbirovi, 0);
		Collections.sort(zbirovi, Collections.reverseOrder());
		return zbirovi.get(0);
	}
	
	private int fja1(CvorStabla k, CvorStabla c1, CvorStabla c2) {
		if(k==null)
			return 1;
		if(k== c1 || k==c2)  return 1;
		if(this.sadrzi(k, c2) && this.sadrzi(k, c1))
			return k.podatak*fja1(k.levo, c1, c2)*fja1(k.desno, c1, c2);
		return fja1(k.levo, c1, c2)*fja1(k.desno, c1, c2);

	}
	
	public int proizvodZajednickihPredakaDvaCvora(CvorStabla koren, CvorStabla c1, CvorStabla c2) throws LabisException {
		if(koren==null || c1==null || c2==null)
			throw new LabisException("Pogresan unos");
		if(!this.sadrzi(koren, c1) || !this.sadrzi(koren, c2))
			throw new LabisException("Stablo je sadrzi cvor");
		int pr= fja1(koren, c1, c2);
		
		if(pr==1 && koren.podatak!=1)
			throw new LabisException("Greska");
		return pr;
	}

	
	private void cloneRek(CvorStabla novi, CvorStabla stari) {
		if(novi==null || stari==null)
			return;
		if(stari.levo==null)
			novi.levo=null;
		else
			novi.levo=new CvorStabla(stari.levo.podatak);
		if(stari.desno==null)
			novi.desno=null;
		else
			novi.desno=new CvorStabla(stari.desno.podatak);
		cloneRek(novi.levo, stari.levo);
		cloneRek(novi.desno, stari.desno);
		
	}
	
	protected Object clone() throws CloneNotSupportedException {
		if(this.koren==null)
			return null;
		CvorStabla k=new CvorStabla(this.koren.podatak);
		cloneRek(k, this.koren);
		BinarnoStablo bs=new BinarnoStablo();
		StabloGenerator.izgenerisiStablo(bs);
		bs.koren=k;
		return (Object)bs;
	}
	
	public void ispis(CvorStabla k) {
	if(k==null)
		return;
	ispis(k.levo);
	System.out.println(k.podatak);
	ispis(k.desno);
		

	}
}