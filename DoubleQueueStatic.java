package DoubleQueue;

import javax.swing.text.AbstractDocument.BranchElement;

public class DupliRedStaticki implements DupliRed {

	int brojElemenata=0;
	int p=-1;   //prvi element u redu, index
	int k=-1;   //poslednji element u redu, index
	int[] niz;
	public DupliRedStaticki(int t) {
		this.niz=new int[t];
	}
	public DupliRedStaticki() {
		this(3);
	}
	@Override
	public void dodajNaPocetak(int z) throws Exception {
		if(this.isFull())
			throw new Exception("Red je pun, ne moze se dodati "+z);
		if(brojElemenata==0) {
			p=0;
			k=0;
			niz[0]=z;
			brojElemenata++;
			return;
		}
		p--;
		if(p==-1) {
			p=niz.length-1;
		}
		niz[p]=z;
		
		brojElemenata++;
	}

	@Override
	public void dodajNaKraj(int z) throws Exception {
		if(this.isFull())
			throw new Exception("Red je pun, ne moze se dodati "+z);
		if(brojElemenata==0) {
			p=0;
			k=0;
			niz[0]=z;
			brojElemenata++;
			return;
		}
		k++;
		if(k==niz.length) {
			k=0;;
		}
		niz[k]=z;

		brojElemenata++;
	}

	@Override
	public int izbaciSaKraja() throws Exception {
		if(this.isEmpty())
			throw new Exception("Red je prazan, ne moze se nista izbaciti");
		if(brojElemenata==1) {
			p=-1;
			k=-1;
			brojElemenata--;
			return niz[0];
		}
		int temp=k;
		k--;
		if(k==-1)
			k=niz.length-1;
		brojElemenata--;
		return niz[temp];
	}

	@Override
	public int izbaciSaPocetka() throws Exception {
		if(this.isEmpty())
			throw new Exception("Red je prazan, ne moze se nista izbaciti");
		if(brojElemenata==1) {
			p=-1;
			k=-1;
			brojElemenata--;
			return niz[0];
		}
		int temp=p;
		p++;
		if(p==niz.length)
			p=0;
		brojElemenata--;
		return niz[temp];
	}

	@Override
	public void porcitajSve() {
		int r1=p;
		for(int i=0; i<brojElemenata;i++) {
			if(r1==niz.length)
				r1=0;
			System.out.println(niz[r1]);
			r1++;
		}
		
	}

	@Override
	public int procitajPrvi() throws Exception {
		if(this.isEmpty())
			throw new Exception("Red je prazan, ne moze se procitati");
		return niz[p];
	}

	@Override
	public int procitajPoslednji() throws Exception {
		if(this.isEmpty())
			throw new Exception("Red je prazan, ne moze se procitati");
		return niz[k];
	}

	@Override
	public Boolean isFull() {
		
		return brojElemenata==niz.length;
	}

	@Override
	public Boolean isEmpty() {
		return brojElemenata==0;
	}

}
