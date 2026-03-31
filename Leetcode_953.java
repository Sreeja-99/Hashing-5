//Place all chars of order in a map with it's index for O(1) search
//Travel word by word from words
//Check if they are lexicographically sorted or not
//If not return false
//ELse, return true
//TC: O(N*L) N-> avg number of words; L->avg length of word
//SC: O(1)
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0;i<order.length();i++){
            map.put(order.charAt(i),i);
        }

        for(int i =0;i<words.length-1;i++){
            String word1=words[i];
            String word2=words[i+1];

            if(isNotSorted(word1,word2,map)){
                return false;
            }
        }

        return true;
    }

    private boolean isNotSorted(String word1,String word2, Map<Character,Integer> map){
        int m=word1.length();
        int n=word2.length();

        for(int i=0;i<Math.min(m,n);i++){
            char fchar=word1.charAt(i);
            char schar=word2.charAt(i);

            if(fchar!=schar){
                return map.get(fchar)>map.get(schar);
            }
        }

        if(m>n) return true;

        return false;
    }
}
