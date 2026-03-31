//For the given strings - build a graph
//Perform topological sort of graph
//If nothing is added to queue - there exists a cycle - return empty string
//If everything is added to queue - all are independent chars - return result
//Once indegree becomes 0, add it to queue, result and increment count
//Pop elements from queue and process
//TC: O(V+E)
//SC: O(1)
class Solution {
    public String alienOrder(String[] words) {
        Map<Character,Set<Character>> map=new HashMap<>();
        int[] indegree=new int[26];


        buildGraph(words, map, indegree);
        //Topological sort
        Queue<Character> queue=new LinkedList<>();
        int count=0;
        StringBuilder result=new StringBuilder();
        for(char ch:map.keySet()){
            if(indegree[ch-'a']==0){
                queue.add(ch);
                result.append(ch);
                count+=1;
            }
        }
        //If everything is added to queue
        if(count==map.size()) return result.toString();
        //If queue is empty
        if(queue.isEmpty()) return "";

        while(!(queue.isEmpty())){
            char curr=queue.poll();
            Set<Character> set=map.get(curr);

            for(char ch:set){
                indegree[ch-'a']-=1;
                if(indegree[ch-'a']==0){
                    queue.add(ch);
                    result.append(ch);
                    count+=1;
                }
                if(count==map.size()) return result.toString();

            }
        }

        return "";
    }

    private void buildGraph(String[] words, Map<Character,Set<Character>> map, int[] indegree){
        for(String word:words){
            for(int i=0;i<word.length();i++){
                if(!(map.containsKey(word.charAt(i)))){
                    map.put(word.charAt(i),new HashSet<>());
                }
            }
        }

        for(int i=0;i<words.length-1;i++){
            String word1=words[i];
            String word2=words[i+1];

            int m=word1.length();
            int n=word2.length();

            for(int j=0;j<Math.min(m,n);j++){
                //transform
                //To handle this testcase: words = ["abc","ab"]
                if(word1.startsWith(word2) && m>n){
                    map.clear();
                    return;
                }

                char fchar=word1.charAt(j);
                char schar=word2.charAt(j);

                if(fchar!=schar){
                    Set<Character> set=map.get(fchar);
                    if(!(set.contains(schar))){
                        map.get(fchar).add(schar);
                        indegree[schar-'a']+=1;
                    }
                    break;
                }
            }
        }
    }
}
