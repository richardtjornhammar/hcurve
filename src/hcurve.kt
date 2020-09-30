package org.kotlinlang.play

val Boolean.int 
     get() = if (this) 1 else 0

fun rot( n: Int, rp:MutableList<Int>, rq:MutableList<Int> ):MutableList<Int> {
    if (rq[1] == 0) {
        if (rq[0] == 1) {
            rp[0] = n-1 - rp[0];
            rp[1] = n-1 - rp[1];
        }
        //SWAP
        var t : Int = rp[0];
        rp[0] = rp[1];
        rp[1] = t;
    }
    return(rp);
}

fun r2d ( n: Int , rp:MutableList<Int> ) : Int {
    var rq:MutableList<Int>  = mutableListOf(0,0) ;
    var s :Int ; 
    var d :Int = 0 ;
    s = n/2 ;
    while (s>0) {
        rq[0] = ((rp[0] and s) > 0).int;
        rq[1] = ((rp[1] and s) > 0).int;
        d += s * s * ((3 * rq[0]) xor rq[1]);
        rot(n, rp, rq);
        s/=2;
    }
    return d;
}

fun d2r( n:Int, d:Int ) : MutableList<Int> {
    var rp :MutableList<Int> = mutableListOf(0,0);
    var rq :MutableList<Int> = mutableListOf(0,0);
    var  s :Int ; 
    var  t :Int = d ;
    s = 1 ;
    while ( s<n ) {
        rq[0] = 1 and (t/2);
        rq[1] = 1 and (t xor rq[0]);
        rot(s, rp, rq);
        rp[0] += s * rq[0];
        rp[1] += s * rq[1];
        t /= 4;
        s *= 2;
    }
    return ( rp );
}

fun assign_lin_nn ( n:Int , d0 : Int ) :  MutableList<Int>
{
  var nn : MutableList<Int> = mutableListOf( d0 ) ;
  var r : MutableList<Int> = d2r ( n,d0 ) ;
  
  nn.add( r2d( n , mutableListOf( ((r[0]-1)>=0).int*(r[0]-1)+(n-1)*((r[0]-1)<0).int ,r[1] ) ) );
  nn.add( r2d( n , mutableListOf( ((r[0]+1) <n).int*(r[0]+1),r[1]   ) ) );
  nn.add( r2d( n , mutableListOf( r[0],((r[1]-1)>=0).int*(r[1]-1)+(n-1)*((r[1]-1)<0).int ) ) );
  nn.add( r2d( n , mutableListOf( r[0],((r[1]+1) <n).int*(r[1]+1)   ) ) );
  return(nn);
}

fun main() 
{
  var d:Int=0;
  val n:Int=4;

  while( d<n*n )
  {
    val rt = d2r( n , d );
    var crd:String ="";
    for (element in rt) { crd+=" "+element.toString() } 

    print("# " )
    for ( element in rt) {
        print(element.toString()+" ");
    }
    println ( ) ;
    println ( "PO: "+d.toString() + "\t" + rt.toString()  + "\t" + assign_lin_nn ( n , d ).toString() + " > " + r2d(n,rt).toString() );
    var S:String ="NN:";
    for(q in assign_lin_nn ( n , d )){
       S += " "+d2r( n , q ) .toString();
    };
    println(S);
    d+=1;
  }
}
