package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Ratan on 7/29/2015.
 */
public class UpdatesFragment extends Fragment {
    Button buttonapti;
//    Button button;

    Button buttontech;
    Button at1;
    Button at2;
    Button at3;
    Button tt1;
    Button tt2;
    Button tt3;
    View v;
    Button button;
    static Integer testno=1;
    Integer i=0;
    Integer j=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.updates_layout,null);
        try {
            v =inflater.inflate(R.layout.updates_layout,container,false);
        } catch (Exception e) {
            Toast.makeText(getActivity(),"Test's Not updated",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        buttonapti=(Button)v.findViewById(R.id.button4);
//        button=(Button)v.findViewById(R.id.button);

        buttontech=(Button)v.findViewById(R.id.button5);
        at1=(Button)v.findViewById(R.id.at1);
        at2=(Button)v.findViewById(R.id.at2);
        at3=(Button)v.findViewById(R.id.at3);
        tt1=(Button)v.findViewById(R.id.tt1);
        tt2=(Button)v.findViewById(R.id.tt2);
        tt3=(Button)v.findViewById(R.id.tt3);
        button=(Button)v.findViewById(R.id.button);


        at1.setVisibility(View.GONE);
        at2.setVisibility(View.GONE);
        at3.setVisibility(View.GONE);
        tt1.setVisibility(View.GONE);
        tt2.setVisibility(View.GONE);
        tt3.setVisibility(View.GONE);

button.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.Quiz");
                startActivity(i3);
            }
        }
);



            buttonapti.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (i == 0) {
                                i=1;
                                at1.setVisibility(View.VISIBLE);
                                at2.setVisibility(View.VISIBLE);
                                at3.setVisibility(View.VISIBLE);
                                tt1.setVisibility(View.GONE);
                                tt2.setVisibility(View.GONE);
                                tt3.setVisibility(View.GONE);
                             /*   buttonapti.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                at1.setVisibility(View.GONE);
                                                at2.setVisibility(View.GONE);
                                                at3.setVisibility(View.GONE);
                                                i=1;
                                            }
                                        }

                                );
*/
                                   
                            }


                            else
                            {
                                            i=0;
                                at1.setVisibility(View.GONE);
                                at2.setVisibility(View.GONE);
                                at3.setVisibility(View.GONE);
/*
                                i=0;
                                at1.setVisibility(View.VISIBLE);
                                at2.setVisibility(View.VISIBLE);
                                at3.setVisibility(View.VISIBLE);
                                tt1.setVisibility(View.GONE);
                                tt2.setVisibility(View.GONE);
                                tt3.setVisibility(View.GONE);
                                buttonapti.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                at1.setVisibility(View.GONE);
                                                at2.setVisibility(View.GONE);
                                                at3.setVisibility(View.GONE);
                                                i=0;

                                            }
                                        }
                                );
*/



                            }
                        }
                    }
            );




              buttontech.setOnClickListener(
                      new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {

                              if (j == 0) {
                                  j=1;
                                  at1.setVisibility(View.GONE);
                                  at2.setVisibility(View.GONE);
                                  at3.setVisibility(View.GONE);
                                  tt1.setVisibility(View.VISIBLE);
                                  tt2.setVisibility(View.VISIBLE);
                                  tt3.setVisibility(View.VISIBLE);
  /*                                buttontech.setOnClickListener(
                                          new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  tt1.setVisibility(View.GONE);
                                                  tt2.setVisibility(View.GONE);
                                                  tt3.setVisibility(View.GONE);
                                                  j=1;
                                              }
                                          }
                                  );
*/
                              } else {
                                  j=0;
                                  tt1.setVisibility(View.GONE);
                                  tt2.setVisibility(View.GONE);
                                  tt3.setVisibility(View.GONE);

                       /*           at1.setVisibility(View.GONE);
                                  at2.setVisibility(View.GONE);
                                  at3.setVisibility(View.GONE);
                                  tt1.setVisibility(View.VISIBLE);
                                  tt2.setVisibility(View.VISIBLE);
                                  tt3.setVisibility(View.VISIBLE);
                                  buttontech.setOnClickListener(
                                          new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  tt1.setVisibility(View.GONE);
                                                  tt2.setVisibility(View.GONE);
                                                  tt3.setVisibility(View.GONE);
                                                  j = 0;
                                              }
                                          }
                                  );

*/

                              }
                          }
                      }
              );




              at1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                           testno=1;
                        Intent intent=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.CardFlipActivity");
                        startActivity(intent);
                    }
                }
        );
        at2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testno = 2;
                        Intent intent2 = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.CardFlipActivity");
                        startActivity(intent2);
                    }
                }
        );

        at3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testno = 3;
                        Intent intent3 = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.CardFlipActivity");
                        startActivity(intent3);
                    }
                }
        );

        tt1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testno=1;
                        Intent intent4=new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.CardFlipActivity1");
                        startActivity(intent4);
                    }
                }
        );
        tt2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testno = 2;
                        Intent intent5 = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.CardFlipActivity1");
                        startActivity(intent5);
                    }
                }
        );

        tt3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testno = 3;
                        Intent intent6 = new Intent("subhamguptaminor.androidbelieve.drawerwithswipetabs.CardFlipActivity1");
                        startActivity(intent6);
                    }
                }
        );







        return v;






    }
}
