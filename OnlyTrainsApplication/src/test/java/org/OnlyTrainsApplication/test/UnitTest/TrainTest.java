package org.OnlyTrainsApplication.test.UnitTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TrainTest {
    // Mocker ArtistRepositoryPort slik at vi kan definere hvordan denne skal fungere i testene våre
    @Mock
    ArtistRepositoryPort mockArtistRepository;
}
