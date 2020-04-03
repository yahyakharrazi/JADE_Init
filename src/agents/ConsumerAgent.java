package agents;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

public class ConsumerAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("********************");
        System.out.println("*Agent init "+this.getAID().getName() +"*");
        if (this.getArguments().length == 1)
            System.out.println("je vais tenter de acheter le livre : "+getArguments()[0]);
        System.out.println("********************");

        ParallelBehaviour pb = new ParallelBehaviour();
        pb.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage= receive();
                if (aclMessage!=null){
                    System.out.println("**************");
                    System.out.println("Reception du message");
                    System.out.println(aclMessage.getContent());
                    System.out.println(aclMessage.getLanguage());
                    System.out.println(aclMessage.getPerformative());
                    System.out.println(aclMessage.getOntology());
                    System.out.println(aclMessage.getSender().getName());

                    ACLMessage reply = aclMessage.createReply();
                    reply.setContent("I see something in your eyes!");
                    send(reply);

                }
                else
                    block();
            }
        });
        addBehaviour(pb);

//        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
//        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,1000) {
//            @Override
//            protected void onTick() {
//                System.out.println(myAgent.getAID().getName());
//            }
//        });
//
//        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
//            @Override
//            public void action() {
//                System.out.println("cycle ...");
//            }
//        });
//
//        parallelBehaviour.addSubBehaviour(new OneShotBehaviour(){
//            @Override
//            public void action() {
//                System.out.println("Action ...");
//            }
//        });
//
//        parallelBehaviour.addSubBehaviour(new Behaviour() {
//            private int cmpt = 0;
//            @Override
//            public void action() {
//                ++cmpt;
//                System.out.println("Etape "+ cmpt);
//            }
//
//            @Override
//            public boolean done() {
//                if(cmpt==10)
//                    return true;
//                return false;
//            }
//        });
//
//        addBehaviour(parallelBehaviour);
    }

    @Override
    protected void beforeMove() {
        System.out.println("**************************");
        System.out.println("*avant migration*");
        System.out.println("**************************");
    }

    @Override
    protected void afterMove() {
        System.out.println("**************************");
        System.out.println("*apres migration*");
        System.out.println("**************************");
    }

    @Override
    protected void takeDown() {
        System.out.println("**************************");
        System.out.println("*kill me kill me kill me ...*");
        System.out.println("**************************");
    }
}
