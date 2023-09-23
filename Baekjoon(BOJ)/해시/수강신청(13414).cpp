#include<bits/stdc++.h>
#include<unordered_map>
using namespace std;

unordered_map<string, int> student;
int L, K;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	cin >> K >> L;

	string studentNum;
	for (int i = 1; i <= L; i++) {
		cin >> studentNum;		
		if (student.find(studentNum) != student.end()) {
			student.erase(studentNum);
			student[studentNum] = i;
		}
		else
			student[studentNum] = i;
	}

	vector<pair<string, int>> sList(student.begin(), student.end());
	sort(sList.begin(), sList.end(), [](auto& a, auto& b) {return a.second < b.second; });
	
	int cnt = min(K, (int)sList.size());

	for (int i = 0; i < cnt; i++)
		cout << sList[i].first << "\n";
	
} 