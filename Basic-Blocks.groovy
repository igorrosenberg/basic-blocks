/*
class BooleanLookup1 {
   static table = [(index(true,true,true)):'A', (index(true,false,true)): 'B']
   static index(x,y,z) { '' + x + y + z }
   static find(x,y,z) {table[index(x,y,z)]}
}

class BooleanLookup2 {
   static table = [(index(true,true,true)):'A', (index(true,false,true)): 'B']
   static index(x,y,z) { hint(x,2) + hint(y,1) + hint(z,0) }
   static find(x,y,z) {table[index(x,y,z)]}
   static hint(x,y){ (x?1:0) << y }
}


class BooleanLookup3 {
   static table = build 8, [(index(true,true,true)):'A', (index(true,false,true)): 'B']
   private static index(x,y,z) { hint(x,2) + hint(y,1) + hint(z,0) }

   private static hint(x,y){ (x?1:0) << y }
   static find(x,y,z) {table[index(x,y,z)]}
   private static build(size,l) {
        def liste = new Object[size] ;
        l.each{k,v ->
            if (liste[k])
                throw new ArrayIndexOutOfBoundsException('duplicate index ' + k) ;
            liste[k] = v
            }
        }
}
*/

class BooleanTruthTable {
   final protected table
   def value(array) { this.table[index(array)] }
   BooleanTruthTable(indexNumber, elements) {
        def liste = new Object[2^indexNumber] ;
        elements.each { k,v ->
            def tableIndex = index(k)
            if (liste[tableIndex])
                throw new ArrayIndexOutOfBoundsException("Duplicate index $k = $tableIndex") ;
            liste[tableIndex] = v
            }
	this.table = liste
        }

   protected asInt(x,y){ (x?1:0) << y }
   protected index(array) {array.collectWithIndex{x,i -> asInt(x,array.length - i)}.sum() }
}

def b = new BooleanTruthTable(3, [[true,true,true]:'A', [true,false,true]: 'B']
assert b.value(true,false,true) == 'B'
assert ! b.value(false,false,false)

