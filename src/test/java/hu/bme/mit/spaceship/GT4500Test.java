package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore secondaryTSMock = mock(TorpedoStore.class);
  TorpedoStore primaryTSMock = mock(TorpedoStore.class);

  @BeforeEach
  public void init(){

    when(primaryTSMock.fire(1)).thenReturn(true);
    when(secondaryTSMock.fire(1)).thenReturn(true);

    when(primaryTSMock.isEmpty()).thenReturn(false);
    when(secondaryTSMock.isEmpty()).thenReturn(false);

    this.ship = new GT4500(primaryTSMock, secondaryTSMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert and verify
    verify(primaryTSMock, times(1)).isEmpty();
    verify(primaryTSMock, times(1)).fire(1);

    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert and verify
    verify(primaryTSMock, times(1)).isEmpty();
    verify(primaryTSMock, times(1)).fire(1);

    verify(secondaryTSMock, times(1)).isEmpty();
    verify(secondaryTSMock, times(1)).fire(1);

    assertEquals(true, result);
  }

}
