import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

/*

forest - undirected graph without cycles (not all connected)

both of them have forest with nodes from 1 to n

add edges such that:
- both of their graphs are still forests
- if edge (u, v) is added to mocha, then edge(u, v) is also added to diana (vice-versa)

 */

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		//T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), m = fs.nextInt(), d = fs.nextInt();
			int[][] parents = new int[2][n+1];
			for (int i = 1; i <= n; i++) {
				parents[0][i] = i;
				parents[1][i] = i;
			}
			for (int i = 0; i < m; i++) {
				int u = fs.nextInt(), v = fs.nextInt();
				int uPar = getParent(parents, 0, u);
				int vPar = getParent(parents, 0, v);
				parents[0][uPar] = vPar;
			}
			//printMatrix(f, n);
			for (int i = 0; i < d; i++) {
				int u = fs.nextInt(), v = fs.nextInt();
				int uPar = getParent(parents, 1, u);
				int vPar = getParent(parents, 1, v);
				parents[1][uPar] = vPar;
			}
			//printMatrix(f, n);
			ArrayList<Pair> arr = new ArrayList<>();
			for (int i = 1; i <= n; i++) {
				for (int j = i + 1; j <= n; j++) {
					if (getParent(parents, 0, i) != getParent(parents, 0, j) && getParent(parents, 1, i) != getParent(parents, 1, j)) {
						arr.add(new Pair(i, j));
						parents[0][getParent(parents, 0, i)] = getParent(parents, 0, j);
						parents[1][getParent(parents, 1, i)] = getParent(parents, 1, j);
					}
				}
			}
			out.println(arr.size());
			for (Pair p : arr) {
				out.println(p.first + " " + p.second);
			}
		}
		out.close();
	}
	
	static int getParent(int[][] parents, int id, int x) {
		if (x == parents[id][x]) {
			return x;
		}
		return parents[id][x] = getParent(parents, id, parents[id][x]);
	}
	
	static void printMatrix(int[][] f, int n) {
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(f[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Pair {
		int first;
		int second;
		
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
	
	static final Random rnd = new Random();
	
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newIndex = rnd.nextInt(n);
			int temp = a[newIndex]; //change this
			a[newIndex] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
