#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;
typedef pair<int, int> p;

int N, M, res = 2147000000;
vector <p> vHome;
vector <p> vPizza;
int ch[13];


void DFS(int level, int idx) {

	int hx, hy, px, py, dis;

	if (level == M) {
		int sum = 0;
		for (int i = 0; i < vHome.size(); i++) {
			int min = 2147000000;
			hx = vHome[i].first;
			hy = vHome[i].second;

			for (int j = 0; j < M; j++) {
				px = vPizza[ch[j]].first;
				py = vPizza[ch[j]].second;
				dis = abs(px - hx) + abs(py - hy);

				if (dis < min) 
					min = dis;				
			}
			sum += min;
		}
		if (res > sum)
			res = sum;
		return;
	}

	for (int i = idx; i < vPizza.size(); i++) {
		ch[level] = i;
		DFS(level + 1, i + 1);
	}

}

int main() {

	ios::sync_with_stdio(0); cin.tie(0);
	cin >> N >> M;
	int n;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> n;
			if (n == 1)
				vHome.push_back({ i,j });
			if (n == 2)
				vPizza.push_back({ i,j });
		}
	}

	DFS(0, 0);
	cout << res;

	return 0;
}