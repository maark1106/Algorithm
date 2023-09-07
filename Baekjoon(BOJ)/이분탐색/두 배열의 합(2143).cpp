#include<bits/stdc++.h>
using namespace std;

int A[1001];
int B[1001];
int aN, bN, target;

vector<int> sumA;
vector<int> sumB;


int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> target;

	cin >> aN;
	for (int i = 0; i < aN; i++)
		cin >> A[i];

	cin >> bN;
	for (int i = 0; i < bN; i++)
		cin >> B[i];

	for (int i = 0; i < aN; i++) {
		int sum = 0;
		for (int j = i; j < aN; j++) {
			sum += A[j];
			sumA.push_back(sum);
		}
	}

	for (int i = 0; i < bN; i++) {
		int sum = 0;
		for (int j = i; j < bN; j++) {
			sum += B[j];
			sumB.push_back(sum);
		}
	}

	sort(sumA.begin(), sumA.end());
	sort(sumB.begin(), sumB.end());


	long long res = 0;
	for (int i = 0; i < sumA.size(); i++) {
		
		int low = lower_bound(sumB.begin(), sumB.end(), target - sumA[i]) - sumB.begin();
		int high = upper_bound(sumB.begin(), sumB.end(), target - sumA[i]) - sumB.begin();
		res += high - low;
			
	}

	cout << res;
	
	
	return 0;
}