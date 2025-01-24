
public class CvorStabla {

	int podatak;
	CvorStabla levi=null;
	CvorStabla desni=null;
	public CvorStabla(int p, CvorStabla l, CvorStabla d) {
		podatak=p;
		levi=l;
		desni=d;
	}
}
