
# ICL-Project

## Implement a complete interpreter for the basic imperative-functional language specified

## Abstract Syntax

    EE -> EE ; EE | EE := EE
    
    | num | id | bool | let (id = EE)+ in EE end
    
    | fun id*-> EE end
    
    | EE ( EE* )
    
    | new EE | <!> EE
    
    | if EE then EE else EE end
    
    | while EE do EE end
    
    | EE binop EE
    
    | unop EE

## Concrete Syntax

    EM -> E(<;>EM)*
    
    E -> EA(< == > EA)? 
    
    EA -> T(<+>EA)*
    
    T -> F ( (<*>T) *
    
    | (<(>AL<)>)*
    
    | <:=> E)
    
    AL -> (EM(<,>AL)*)?
    
    PL -> (id(<,>PL)*)?
    
    F -> num | id | bool | let (id = EM)+ in EM end
    
    | fun PL -> EM end | <(> EM <)>
    
    | new F | <!> F
    
    | if EM then EM else EM end 
    
    | while EM do EM end

## Basic operations

**Arithmetic operations (on integer values)** 

    E+E, E-E, E*E, E/E, -E

**Relational Operations**

    E==E, E>E, E<E, E<=E, E>=E
    
**Logical operations (on boolean values)**
 
    E && E, E || E, ~E
    
## Examples

    (new 3) := 6;;
    
    let a = new 5 in a := !a + 1; !a end;;
    
    let x = new 10 
    
        s = new 0 in
        
    while !x>0 do
    
        s := !s + !x ; x := !x – 1
    end; !s 
    
    end;;

    --------------------------------------------
    
    let f=fun n,b->
    
            let
            
              x = new n 
              
              s = new b
              
            in
                while !x>0 do
                
                    s := !s + !x ; x := !x – 1
                    
                end;
                
                !s
                
            end
           
           end
           
    in f(10,0)+f(100,20)
    
    end;;
                

    
